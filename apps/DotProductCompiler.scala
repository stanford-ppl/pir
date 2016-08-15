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
    val x903_argout = ArgOut()
    val x997_vector = Vector()
    val x1029_scalar = Scalar()
    val x909_argin = ArgIn()
    val x905_oc = OffChip()
    val x1011_vector = Vector()
    val x906_oc = OffChip()
    val x1012_mc_mc = MemoryController(TileLoad, x906_oc)
    val x998_mc_mc = MemoryController(TileLoad, x905_oc)
    val x1061 = ComputeUnit(name="x1061", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1061_unitCC = CounterChain(name = "x1061_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1058 = ComputeUnit(name="x1058", parent=x1061, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x911 = (Const("0i").out, CU.scalarIn(stage0, x909_argin).out, Const("19200i").out) // Counter
      val x912 = CounterChain(name = "x912", x911)
    }
    val x1010 = ComputeUnit(name="x1010", parent=x1058, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1010_unitCC = CounterChain(name = "x1010_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x998 = TileTransfer(name="x998", parent=x1010, memctrl=x998_mc_mc, mctpe=TileLoad, deps=List(), vec=x997_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x912 = CounterChain.copy(x1058, "x912")
      val x998_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x998_cc = CounterChain(name = "x998_cc", x998_ctr)
      val x999 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1000 = CounterChain(name = "x1000", x999).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x912(0)), CU.ctr(stage(0), x998_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x998_mc_mc.saddr)))
    }
    val x1024 = ComputeUnit(name="x1024", parent=x1058, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1024_unitCC = CounterChain(name = "x1024_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1012 = TileTransfer(name="x1012", parent=x1024, memctrl=x1012_mc_mc, mctpe=TileLoad, deps=List(), vec=x1011_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x912 = CounterChain.copy(x1058, "x912")
      val x1012_ctr = (Const("0l").out, Const("19200i").out, Const("1l").out) // Counter
      val x1012_cc = CounterChain(name = "x1012_cc", x1012_ctr)
      val x1013 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1014 = CounterChain(name = "x1014", x1013).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x912(0)), CU.ctr(stage(0), x1012_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1012_mc_mc.saddr)))
    }
    val x1045 = ComputeUnit(name="x1045", parent=x1058, tpe = Pipe, deps=List(x1010, x1024)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1014 = CounterChain.copy(x1012, "x1014")
      val x1027 = (Const("0i").out, Const("19200i").out, Const("1i").out) // Counter
      val x1028 = CounterChain(name = "x1028", x1027)
      val x1000 = CounterChain.copy(x998, "x1000")
      val x995_x1035 = SRAM(size = 19200, swapCtr = x1000(0), writeCtr = x1000(0), banking = Strided(1), doubleBuffer = true).wtPort(x997_vector).rdAddr(x1028(0)).wtAddr(x1000(0))
      val x996_x1036 = SRAM(size = 19200, swapCtr = x1014(0), writeCtr = x1014(0), banking = Strided(1), doubleBuffer = true).wtPort(x1011_vector).rdAddr(x1028(0)).wtAddr(x1014(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x995_x1035.load, x996_x1036.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr53) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr53), op=Bypass, results=List(CU.scalarOut(stage(2), x1029_scalar)))
    }
    val x1056 = UnitComputeUnit(name ="x1056", parent=x1058, deps=List(x1045)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar57 = CU.accum(init = Const("0i"))
      val x1056_unitCC = CounterChain(name = "x1056_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1029_scalar), CU.accum(stage(1), ar57)), op=FixAdd, results=List(CU.scalarOut(stage(1), x903_argout), CU.accum(stage(1), ar57)))
    }
    
  }
}
