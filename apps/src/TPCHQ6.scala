import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object TPCHQ6Design extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3191_argin = ArgIn("x3191")
    val x3200_argout = ArgOut("x3200")
    val x3385_scalar = Scalar("x3385")
    val x3197_oc = OffChip("x3197")
    val x3198_oc = OffChip("x3198")
    val bus_209_vector = Vector("bus_209")
    val x3196_oc = OffChip("x3196")
    val x3205_scalar = Scalar("x3205")
    val x3199_oc = OffChip("x3199")
    val bus_198_vector = Vector("bus_198")
    val x3322_mc = MemoryController(TileLoad, x3198_oc)
    val x3364_mc = MemoryController(TileLoad, x3199_oc)
    val x3280_mc = MemoryController(TileLoad, x3197_oc)
    val x3238_mc = MemoryController(TileLoad, x3196_oc)
    val x3429 = Sequential(name = "x3429", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3429_unitcc = CounterChain(name = "x3429_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3423 = MetaPipeline(name = "x3423", parent=x3429, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x3191_argin).out, Const("96i").out) // Counter
      val x3208 = CounterChain(name = "x3208", ctr1)
      var stage: List[Stage] = Nil
    }
    val x3256 = StreamController(name = "x3256", parent=x3423, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3256_unitcc = CounterChain(name = "x3256_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3234_0 = UnitPipeline(name = "x3234_0", parent=x3256, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr104 = CU.temp
      val tr103 = CU.temp
      val tr101 = CU.temp
      val tr100 = CU.temp
      val tr98 = CU.temp
      val tr94 = CU.temp
      val x3208 = CounterChain.copy(x3423, "x3208")
      val x3234_unitcc = CounterChain(name = "x3234_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3208(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr94)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3208(0)), CU.temp(stage(1), tr94)), op=FixSub, results=List(CU.scalarOut(stage(2), x3238_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr94), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr98)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr98), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr100)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr98), CU.temp(stage(4), tr100)), op=FixSub, results=List(CU.temp(stage(5), tr101)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr100), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr103)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr103), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr104)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr101), CU.temp(stage(7), tr104)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3238_mc.len)))
    }
    val x3298 = StreamController(name = "x3298", parent=x3423, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3298_unitcc = CounterChain(name = "x3298_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3276_0 = UnitPipeline(name = "x3276_0", parent=x3298, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr126 = CU.temp
      val tr125 = CU.temp
      val tr123 = CU.temp
      val tr122 = CU.temp
      val tr120 = CU.temp
      val tr116 = CU.temp
      val x3208 = CounterChain.copy(x3423, "x3208")
      val x3276_unitcc = CounterChain(name = "x3276_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3208(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr116)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3208(0)), CU.temp(stage(1), tr116)), op=FixSub, results=List(CU.scalarOut(stage(2), x3280_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr116), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr120)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr120), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr122)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr120), CU.temp(stage(4), tr122)), op=FixSub, results=List(CU.temp(stage(5), tr123)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr122), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr125)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr125), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr126)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr123), CU.temp(stage(7), tr126)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3280_mc.len)))
    }
    val x3340 = StreamController(name = "x3340", parent=x3423, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3340_unitcc = CounterChain(name = "x3340_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3318_0 = UnitPipeline(name = "x3318_0", parent=x3340, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr148 = CU.temp
      val tr147 = CU.temp
      val tr145 = CU.temp
      val tr144 = CU.temp
      val tr142 = CU.temp
      val tr138 = CU.temp
      val x3208 = CounterChain.copy(x3423, "x3208")
      val x3318_unitcc = CounterChain(name = "x3318_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3208(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr138)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3208(0)), CU.temp(stage(1), tr138)), op=FixSub, results=List(CU.scalarOut(stage(2), x3322_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr138), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr142)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr142), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr144)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr142), CU.temp(stage(4), tr144)), op=FixSub, results=List(CU.temp(stage(5), tr145)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr144), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr147)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr147), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr148)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr145), CU.temp(stage(7), tr148)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3322_mc.len)))
    }
    val x3382 = StreamController(name = "x3382", parent=x3423, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3382_unitcc = CounterChain(name = "x3382_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3360_0 = UnitPipeline(name = "x3360_0", parent=x3382, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr170 = CU.temp
      val tr169 = CU.temp
      val tr167 = CU.temp
      val tr166 = CU.temp
      val tr164 = CU.temp
      val tr160 = CU.temp
      val x3208 = CounterChain.copy(x3423, "x3208")
      val x3360_unitcc = CounterChain(name = "x3360_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3208(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr160)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3208(0)), CU.temp(stage(1), tr160)), op=FixSub, results=List(CU.scalarOut(stage(2), x3364_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr160), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr164)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr164), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr166)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr164), CU.temp(stage(4), tr166)), op=FixSub, results=List(CU.temp(stage(5), tr167)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr166), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr169)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr169), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr170)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr167), CU.temp(stage(7), tr170)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3364_mc.len)))
    }
    val x3415 = StreamController(name = "x3415", parent=x3423, deps=List(x3256, x3298, x3340, x3382)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x3387 = CounterChain(name = "x3387", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3415_0 = StreamPipeline(name = "x3415_0", parent=x3415, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr197 = CU.temp
      val tr195 = CU.temp
      val x3387 = CounterChain.copy(x3415, "x3387")
      val x3211_x3389 = FIFO(size = 96, banking = Strided(1)).wtPort(x3238_mc.dataOut)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(Const("0i"), x3211_x3389.load), op=FixLt, results=List(CU.temp(stage(1), tr195)))
      Stage(stage(2), operands=List(x3211_x3389.load, Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr197)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr195), CU.temp(stage(2), tr197)), op=BitAnd, results=List(CU.vecOut(stage(3), bus_198_vector)))
    }
    val x3415_1 = StreamPipeline(name = "x3415_1", parent=x3415, deps=List(x3415_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr207 = CU.temp
      val tr206 = CU.temp
      val tr205 = CU.temp
      val tr203 = CU.temp
      val tr202 = CU.temp
      val tr200 = CU.temp
      val tr199 = CU.temp
      val x3387 = CounterChain.copy(x3415, "x3387")
      val x3214_x3395 = FIFO(size = 96, banking = Strided(1)).wtPort(x3364_mc.dataOut)
      val x3213_x3391 = FIFO(size = 96, banking = Strided(1)).wtPort(x3322_mc.dataOut)
      val x3212_x3393 = FIFO(size = 96, banking = Strided(1)).wtPort(x3280_mc.dataOut)
      val bus_198_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_198_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i"), x3213_x3391.load), op=FixLeq, results=List(CU.temp(stage(1), tr199)))
      Stage(stage(2), operands=List(bus_198_fifo.load, CU.temp(stage(1), tr199)), op=BitAnd, results=List(CU.temp(stage(2), tr200)))
      Stage(stage(3), operands=List(x3213_x3391.load, Const("9999i")), op=FixLeq, results=List(CU.temp(stage(3), tr202)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr200), CU.temp(stage(3), tr202)), op=BitAnd, results=List(CU.temp(stage(4), tr203)))
      Stage(stage(5), operands=List(x3212_x3393.load, Const("24i")), op=FixLt, results=List(CU.temp(stage(5), tr205)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr203), CU.temp(stage(5), tr205)), op=BitAnd, results=List(CU.temp(stage(6), tr206)))
      Stage(stage(7), operands=List(x3214_x3395.load, x3213_x3391.load), op=FixMul, results=List(CU.temp(stage(7), tr207)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr206), CU.temp(stage(7), tr207), Const("0i")), op=Mux, results=List(CU.vecOut(stage(8), bus_209_vector)))
    }
    val x3415_2 = StreamPipeline(name = "x3415_2", parent=x3415, deps=List(x3415_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3387 = CounterChain.copy(x3415, "x3387")
      val bus_209_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_209_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_209_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr211) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr211), op=Bypass, results=List(CU.scalarOut(stage(2), x3385_scalar)))
    }
    val x3421_0 = UnitPipeline(name = "x3421_0", parent=x3423, deps=List(x3415)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar215 = CU.accum(init = Const("0i"))
      val x3421_unitcc = CounterChain(name = "x3421_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3385_scalar), CU.accum(stage(1), ar215)), op=FixAdd, results=List(CU.scalarOut(stage(1), x3205_scalar), CU.accum(stage(1), ar215)))
    }
    val x3427_0 = UnitPipeline(name = "x3427_0", parent=x3429, deps=List(x3423)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3427_unitcc = CounterChain(name = "x3427_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3205_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x3200_argout)))
    }
    
  }
}
