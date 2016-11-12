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
    val x1060_scalar = Scalar()
    val x994_vector = Vector()
    val x899_argout = ArgOut()
    val x895_argin = ArgIn()
    val x1008_vector = Vector()
    val x1038_vector = Vector()
    val x1061_scalar = Scalar()
    val x901_oc = OffChip()
    val x1024_vector = Vector()
    val x902_oc = OffChip()
    val x1009_mc_mc = MemoryController(TileLoad, x902_oc)
    val x1025_mc_mc = MemoryController(TileLoad, x901_oc)
    val x995_mc_mc = MemoryController(TileLoad, x901_oc)
    val x1039_mc_mc = MemoryController(TileLoad, x902_oc)
    val x1116 = ComputeUnit(name="x1116", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1116_unitCC = CounterChain(name = "x1116_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1113 = ComputeUnit(name="x1113", parent=x1116, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x906 = (Const("0i").out, CU.scalarIn(stage0, x895_argin).out, Const("19200i").out) // Counter
      val x907 = CounterChain(name = "x907", x906)
    }
    val x1007 = ComputeUnit(name="x1007", parent=x1113, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1007_unitCC = CounterChain(name = "x1007_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x995 = TileTransfer(name="x995", parent=x1007, memctrl=x995_mc_mc, mctpe=TileLoad, deps=List(), vec=x994_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x907 = CounterChain.copy(x1113, "x907")
      val x995_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x995_cc = CounterChain(name = "x995_cc", x995_ctr)
      val x996 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x997 = CounterChain(name = "x997", x996).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x907(0)), CU.ctr(stage(0), x995_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x995_mc_mc.saddr)))
    }
    val x1021 = ComputeUnit(name="x1021", parent=x1113, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1021_unitCC = CounterChain(name = "x1021_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1009 = TileTransfer(name="x1009", parent=x1021, memctrl=x1009_mc_mc, mctpe=TileLoad, deps=List(), vec=x1008_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x907 = CounterChain.copy(x1113, "x907")
      val x1009_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x1009_cc = CounterChain(name = "x1009_cc", x1009_ctr)
      val x1010 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1011 = CounterChain(name = "x1011", x1010).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x907(0)), CU.ctr(stage(0), x1009_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1009_mc_mc.saddr)))
    }
    val x1037 = ComputeUnit(name="x1037", parent=x1113, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1037_unitCC = CounterChain(name = "x1037_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1025 = TileTransfer(name="x1025", parent=x1037, memctrl=x1025_mc_mc, mctpe=TileLoad, deps=List(), vec=x1024_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x907 = CounterChain.copy(x1113, "x907")
      val x1025_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x1025_cc = CounterChain(name = "x1025_cc", x1025_ctr)
      val x1026 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1027 = CounterChain(name = "x1027", x1026).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x907(0)), CU.ctr(stage(0), x1025_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1025_mc_mc.saddr)))
    }
    val x1051 = ComputeUnit(name="x1051", parent=x1113, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1051_unitCC = CounterChain(name = "x1051_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1039 = TileTransfer(name="x1039", parent=x1051, memctrl=x1039_mc_mc, mctpe=TileLoad, deps=List(), vec=x1038_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x907 = CounterChain.copy(x1113, "x907")
      val x1039_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x1039_cc = CounterChain(name = "x1039_cc", x1039_ctr)
      val x1040 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1041 = CounterChain(name = "x1041", x1040).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x907(0)), CU.ctr(stage(0), x1039_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1039_mc_mc.saddr)))
    }
    val x1077 = ComputeUnit(name="x1077", parent=x1113, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x997 = CounterChain.copy(x995, "x997")
      val x1056 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1058 = CounterChain(name = "x1058", x1056)
      val x1011 = CounterChain.copy(x1009, "x1011")
      val x990_x1067 = SRAM(size = 19200, swapCtr = x997(0), writeCtr = x997(0), banking = Strided(1), doubleBuffer = true).wtPort(x994_vector).rdAddr(x1058(0)).wtAddr(x997(0))
      val x992_x1068 = SRAM(size = 19200, swapCtr = x1011(0), writeCtr = x1011(0), banking = Strided(1), doubleBuffer = true).wtPort(x1008_vector).rdAddr(x1058(0)).wtAddr(x1011(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x990_x1067.load, x992_x1068.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr110) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr110), op=Bypass, results=List(CU.scalarOut(stage(2), x1060_scalar)))
    }
    val x1093 = ComputeUnit(name="x1093", parent=x1113, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1027 = CounterChain.copy(x1025, "x1027")
      val x1057 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1059 = CounterChain(name = "x1059", x1057)
      val x1041 = CounterChain.copy(x1039, "x1041")
      val x991_x1083 = SRAM(size = 19200, swapCtr = x1027(0), writeCtr = x1027(0), banking = Strided(1), doubleBuffer = true).wtPort(x1024_vector).rdAddr(x1059(0)).wtAddr(x1027(0))
      val x993_x1084 = SRAM(size = 19200, swapCtr = x1041(0), writeCtr = x1041(0), banking = Strided(1), doubleBuffer = true).wtPort(x1038_vector).rdAddr(x1059(0)).wtAddr(x1041(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x991_x1083.load, x993_x1084.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr121) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr121), op=Bypass, results=List(CU.scalarOut(stage(2), x1061_scalar)))
    }
    val x1111 = UnitComputeUnit(name ="x1111", parent=x1113, deps=List(x1077, x1093)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar126 = CU.accum(init = Const("0i"))
      val tr127 = CU.temp
      val x1111_unitCC = CounterChain(name = "x1111_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1060_scalar), CU.scalarIn(stage(0), x1061_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr127)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr127), CU.accum(stage(2), ar126)), op=FixAdd, results=List(CU.scalarOut(stage(2), x899_argout), CU.accum(stage(2), ar126)))
    }
    
  }
}
