import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProductDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3160_scalar = Scalar("x3160")
    val x3074_oc = OffChip("x3074")
    val x3159_scalar = Scalar("x3159")
    val x3075_oc = OffChip("x3075")
    val x3076_oc = OffChip("x3076")
    val x3158_vector = Vector("x3158")
    val x3184_mc = MemoryController(TileLoad, x3074_oc)
    val x3229_mc = MemoryController(TileLoad, x3075_oc)
    val x3302_mc = MemoryController(TileStore, x3076_oc)
    val x3308 = Sequential(name = "x3308", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3308_unitcc = CounterChain(name = "x3308_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3306 = MetaPipeline(name = "x3306", parent=x3308, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("38400i").out, Const("48i").out) // Counter
      val ctr2 = (Const("0i").out, Const("38400i").out, Const("48i").out) // Counter
      val x3155 = CounterChain(name = "x3155", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x3205 = StreamController(name = "x3205", parent=x3306, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3205_unitcc = CounterChain(name = "x3205_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3180_0 = UnitPipeline(name = "x3180_0", parent=x3205, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr144 = CU.temp
      val tr143 = CU.temp
      val tr141 = CU.temp
      val tr140 = CU.temp
      val tr138 = CU.temp
      val tr133 = CU.temp
      val x3155 = CounterChain.copy(x3306, "x3155")
      val x3180_unitcc = CounterChain(name = "x3180_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3155(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr133)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3155(0)), CU.temp(stage(1), tr133)), op=FixSub, results=List(CU.scalarOut(stage(2), x3184_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr133), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr138)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr138), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr140)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr138), CU.temp(stage(4), tr140)), op=FixSub, results=List(CU.temp(stage(5), tr141)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr140), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr143)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr143), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr144)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr141), CU.temp(stage(7), tr144)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3184_mc.len)))
    }
    val x3250 = StreamController(name = "x3250", parent=x3306, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3250_unitcc = CounterChain(name = "x3250_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3225_0 = UnitPipeline(name = "x3225_0", parent=x3250, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr168 = CU.temp
      val tr167 = CU.temp
      val tr165 = CU.temp
      val tr164 = CU.temp
      val tr162 = CU.temp
      val tr157 = CU.temp
      val x3155 = CounterChain.copy(x3306, "x3155")
      val x3225_unitcc = CounterChain(name = "x3225_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3155(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr157)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3155(1)), CU.temp(stage(1), tr157)), op=FixSub, results=List(CU.scalarOut(stage(2), x3229_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr157), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr162)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr162), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr164)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr162), CU.temp(stage(4), tr164)), op=FixSub, results=List(CU.temp(stage(5), tr165)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr164), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr167)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr167), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr168)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr165), CU.temp(stage(7), tr168)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3229_mc.len)))
    }
    val x3253_0 = UnitPipeline(name = "x3253_0", parent=x3306, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr181 = CU.temp
      val x3155 = CounterChain.copy(x3306, "x3155")
      val x3253_unitcc = CounterChain(name = "x3253_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("38400i"), CU.ctr(stage(0), x3155(0))), op=FixSub, results=List(CU.temp(stage(1), tr181)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr181), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x3159_scalar)))
    }
    val x3256_0 = UnitPipeline(name = "x3256_0", parent=x3306, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr186 = CU.temp
      val x3155 = CounterChain.copy(x3306, "x3155")
      val x3256_unitcc = CounterChain(name = "x3256_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("38400i"), CU.ctr(stage(0), x3155(1))), op=FixSub, results=List(CU.temp(stage(1), tr186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr186), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x3302_mc.len), CU.scalarOut(stage(2), x3160_scalar)))
    }
    val x3282_0 = Pipeline(name = "x3282_0", parent=x3306, deps=List(x3205, x3250, x3253_0, x3256_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x3159_scalar).out, Const("1i").out) // Counter
      val ctr8 = (Const("0i").out, CU.scalarIn(stage0, x3160_scalar).out, Const("16i").out) // Counter
      val x3266 = CounterChain(name = "x3266", ctr7, ctr8)
      val x3156_x3269 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3266(0))).wtPort(x3184_mc.vdata).rdAddr(x3266(0))
      val x3157_x3272 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3266(0))).wtPort(x3229_mc.vdata).rdAddr(x3266(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3156_x3269.load, x3157_x3272.load), op=FixMul, results=List(CU.vecOut(stage(1), x3158_vector)))
    }
    val x3304 = StreamController(name = "x3304", parent=x3306, deps=List(x3282_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, CU.scalarIn(stage0, x3159_scalar).out, Const("1i").out) // Counter
      val x3285 = CounterChain(name = "x3285", ctr13)
      var stage: List[Stage] = Nil
    }
    val x3306_leafX = UnitPipeline(name = "x3306_leafX", parent=x3306, deps=List(x3304)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3155 = CounterChain.copy(x3306, "x3155")
      val x3306_unitcc = CounterChain(name = "x3306_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3289_0 = UnitPipeline(name = "x3289_0", parent=x3304, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr208 = CU.temp
      val tr206 = CU.temp
      val x3155 = CounterChain.copy(x3306, "x3155")
      val x3285 = CounterChain.copy(x3304, "x3285")
      val x3289_unitcc = CounterChain(name = "x3289_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3155(0)), CU.ctr(stage(0), x3285(0))), op=FixAdd, results=List(CU.temp(stage(1), tr206)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr206), Const("38400i")), op=FixMul, results=List(CU.temp(stage(2), tr208)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr208), CU.ctr(stage(2), x3155(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3302_mc.ofs)))
    }
    val x3300_0 = Pipeline(name = "x3300_0", parent=x3304, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr216 = CU.temp
      val tr213 = CU.temp
      val tr212 = CU.temp
      val x3266 = CounterChain.copy(x3282_0, "x3266")
      val ctr15 = (Const("0i").out, CU.scalarIn(stage0, x3160_scalar).out, Const("16i").out) // Counter
      val x3291 = CounterChain(name = "x3291", ctr15)
      val x3285 = CounterChain.copy(x3304, "x3285")
      val x3158_x3294 = SRAM(size = 2304, writeCtr = x3266(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3285(0), swapWrite = x3266(0))).wtPort(x3158_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3158_x3294))
      Stage(stage(1), operands=List(x3266(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr212)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr212), CU.ctr(stage(1), x3266(1))), op=FixAdd, results=List(x3158_x3294.writeAddr, CU.temp(stage(2), tr213)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3285(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr216)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr216), CU.ctr(stage(1), x3291(0))), op=FixAdd, results=List(x3158_x3294.readAddr))
      Stage(stage(3), operands=List(x3158_x3294.load), op=Bypass, results=List(CU.vecOut(stage(3), x3302_mc.vdata)))
    }
    
  }
}
