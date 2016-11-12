import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object DotProductCompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x870_vector = Vector()
    val x816_oc = OffChip()
    val x871_vector = Vector()
    val x882_scalar = Scalar()
    val x817_oc = OffChip()
    val x815_argout = ArgOut()
    val x872_mc_mc = MemoryController(TileLoad, x816_oc)
    val x875_mc_mc = MemoryController(TileLoad, x817_oc)
    val x913 = ComputeUnit(name="x913", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x913_unitCtr = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x913_unit = CounterChain(name = "x913_unit", x913_unitCtr)
    }
    val x910 = ComputeUnit(name="x910", parent=x913, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x820 = (Const("0i").out, Const("93600000i").out, Const("19200i").out) // Counter
      val x821 = CounterChain(name = "x821", x820)
    }
    val x874 = ComputeUnit(name="x874", parent=x910, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x874_unitCtr = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x874_unit = CounterChain(name = "x874_unit", x874_unitCtr)
    }
    val x872 = TileTransfer(name="x872", parent=x874, memctrl=x872_mc_mc, mctpe=TileLoad, deps=List(), vec=x870_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x821 = CounterChain.copy(x910, "x821")
      val x872_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x872_cc = CounterChain(name = "x872_cc", x872_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x821(0)), CU.ctr(stage(0), x872_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x872_mc_mc.saddr)))
    }
    val x877 = ComputeUnit(name="x877", parent=x910, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x877_unitCtr = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x877_unit = CounterChain(name = "x877_unit", x877_unitCtr)
    }
    val x875 = TileTransfer(name="x875", parent=x877, memctrl=x875_mc_mc, mctpe=TileLoad, deps=List(), vec=x871_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x821 = CounterChain.copy(x910, "x821")
      val x875_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x875_cc = CounterChain(name = "x875_cc", x875_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x821(0)), CU.ctr(stage(0), x875_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x875_mc_mc.saddr)))
    }
    val x897 = ComputeUnit(name="x897", parent=x910, tpe = Pipe, deps=List(x874, x877)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x880 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x881 = CounterChain(name = "x881", x880)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x870_vector), CU.vecIn(stage(0), x871_vector)), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr42) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr42), op=Bypass, results=List(CU.scalarOut(stage(2), x882_scalar)))
    }
    val x908 = UnitComputeUnit(name ="x908", parent=x910, deps=List(x897)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar46 = CU.accum(init = Const("0i"))
      val x908_unitCtr = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x908_unit = CounterChain(name = "x908_unit", x908_unitCtr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x882_scalar), CU.accum(stage(1), ar46)), op=FixAdd, results=List(CU.scalarOut(stage(1), x815_argout), CU.accum(stage(1), ar46)))
    }
    
  }
}
