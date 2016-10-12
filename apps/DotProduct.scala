import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProductDesign extends PIRApp {
  override val arch = P2P_4CU_4TT
  def main(args: String*)(top:Top) = {
    val x1132_oc = OffChip()
    val x1223_vector = Vector()
    val x1131_oc = OffChip()
    val x1136_scalar = Scalar()
    val x1255_scalar = Scalar()
    val x1129_argout = ArgOut()
    val x1237_vector = Vector()
    val x1125_argin = ArgIn()
    val x1224_mc_mc = MemoryController(TileLoad, x1131_oc)
    val x1238_mc_mc = MemoryController(TileLoad, x1132_oc)
    val x1291 = Sequential(name="x1291", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1291_unitCC = CounterChain(name = "x1291_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1284 = MetaPipeline(name="x1284", parent=x1291, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1137 = (Const("0i").out, CU.scalarIn(stage0, x1125_argin).out, Const("96i").out) // Counter
      val x1138 = CounterChain(name = "x1138", x1137)
    }
    val x1236 = MetaPipeline(name="x1236", parent=x1284, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1236_unitCC = CounterChain(name = "x1236_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1224 = TileTransfer(name="x1224", parent=x1236, memctrl=x1224_mc_mc, mctpe=TileLoad, deps=List(), vec=x1223_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1138 = CounterChain.copy(x1284, "x1138")
      val x1224_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1224_cc = CounterChain(name = "x1224_cc", x1224_ctr)
      val x1225 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1226 = CounterChain(name = "x1226", x1225).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1138(0)), CU.ctr(stage(0), x1224_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1224_mc_mc.saddr)))
    }
    val x1250 = MetaPipeline(name="x1250", parent=x1284, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1250_unitCC = CounterChain(name = "x1250_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1238 = TileTransfer(name="x1238", parent=x1250, memctrl=x1238_mc_mc, mctpe=TileLoad, deps=List(), vec=x1237_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1138 = CounterChain.copy(x1284, "x1138")
      val x1238_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1238_cc = CounterChain(name = "x1238_cc", x1238_ctr)
      val x1239 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1240 = CounterChain(name = "x1240", x1239).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1138(0)), CU.ctr(stage(0), x1238_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1238_mc_mc.saddr)))
    }
    val x1271 = Pipeline(name="x1271", parent=x1284, deps=List(x1236, x1250)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1253 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1254 = CounterChain(name = "x1254", x1253)
      val x1250_unitCC = CounterChain.copy(x1250, "x1250_unitCC")
      val x1240 = CounterChain.copy(x1238, "x1240")
      val x1226 = CounterChain.copy(x1224, "x1226")
      val x1236_unitCC = CounterChain.copy(x1236, "x1236_unitCC")
      val x1221_x1261 = SRAM(size = 96, writeCtr = x1226(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x1254(0), swapWrite = x1236_unitCC(0))).wtPort(x1223_vector).rdAddr(x1254(0)).wtAddr(x1226(0))
      val x1222_x1262 = SRAM(size = 96, writeCtr = x1240(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x1254(0), swapWrite = x1250_unitCC(0))).wtPort(x1237_vector).rdAddr(x1254(0)).wtAddr(x1240(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1221_x1261.load, x1222_x1262.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr54) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr54), op=Bypass, results=List(CU.scalarOut(stage(2), x1255_scalar)))
    }
    val x1282 = UnitPipeline(name ="x1282", parent=x1284, deps=List(x1271)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar58 = CU.accum(init = Const("0i"))
      val x1282_unitCC = CounterChain(name = "x1282_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1255_scalar), CU.accum(stage(1), ar58)), op=FixAdd, results=List(CU.scalarOut(stage(1), x1136_scalar), CU.accum(stage(1), ar58)))
    }
    val x1288 = UnitPipeline(name ="x1288", parent=x1291, deps=List(x1284)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1288_unitCC = CounterChain(name = "x1288_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1136_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x1129_argout)))
    }
    
  }
}
