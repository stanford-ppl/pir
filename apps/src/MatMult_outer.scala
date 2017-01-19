import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_outerDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x5461_oc = OffChip("x5461")
    val x5462_oc = OffChip("x5462")
    val x5464_oc = OffChip("x5464")
    val x5807_vector = Vector("x5807")
    val x5913_vector = Vector("x5913")
    val x5884_mc = MemoryController(TileLoad, x5462_oc)
    val x5973_mc = MemoryController(TileStore, x5464_oc)
    val x5837_mc = MemoryController(TileLoad, x5461_oc)
    val x5979 = Sequential(name = "x5979", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5979_unitcc = CounterChain(name = "x5979_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5977 = MetaPipeline(name = "x5977", parent=x5979, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val ctr2 = (Const("0i").out, Const("7680i").out, Const("48i").out) // Counter
      val x5806 = CounterChain(name = "x5806", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x5953 = MetaPipeline(name = "x5953", parent=x5977, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5809 = CounterChain(name = "x5809", ctr5)
      var stage: List[Stage] = Nil
    }
    val x5858 = StreamController(name = "x5858", parent=x5953, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x5814 = CounterChain(name = "x5814", ctr7)
      var stage: List[Stage] = Nil
    }
    val x5833_0 = UnitPipeline(name = "x5833_0", parent=x5858, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr260 = CU.temp
      val tr259 = CU.temp
      val tr257 = CU.temp
      val tr256 = CU.temp
      val tr254 = CU.temp
      val tr249 = CU.temp
      val tr247 = CU.temp
      val tr246 = CU.temp
      val tr244 = CU.temp
      val x5833_unitcc = CounterChain(name = "x5833_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5814 = CounterChain.copy(x5858, "x5814")
      val x5806 = CounterChain.copy(x5977, "x5806")
      val x5809 = CounterChain.copy(x5953, "x5809")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5806(0)), CU.ctr(stage(0), x5814(0))), op=FixAdd, results=List(CU.temp(stage(1), tr244)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr244), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr246)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr246), CU.ctr(stage(2), x5809(0))), op=FixAdd, results=List(CU.temp(stage(3), tr247)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr247), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr249)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr247), CU.temp(stage(4), tr249)), op=FixSub, results=List(CU.scalarOut(stage(5), x5837_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr249), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr254)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr254), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr256)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr254), CU.temp(stage(7), tr256)), op=FixSub, results=List(CU.temp(stage(8), tr257)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr256), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr259)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr259), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr260)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr257), CU.temp(stage(10), tr260)), op=FixAdd, results=List(CU.scalarOut(stage(11), x5837_mc.len)))
    }
    val x5905 = StreamController(name = "x5905", parent=x5953, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x5861 = CounterChain(name = "x5861", ctr15)
      var stage: List[Stage] = Nil
    }
    val x5880_0 = UnitPipeline(name = "x5880_0", parent=x5905, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr290 = CU.temp
      val tr289 = CU.temp
      val tr287 = CU.temp
      val tr286 = CU.temp
      val tr284 = CU.temp
      val tr279 = CU.temp
      val tr277 = CU.temp
      val tr276 = CU.temp
      val tr274 = CU.temp
      val x5806 = CounterChain.copy(x5977, "x5806")
      val x5880_unitcc = CounterChain(name = "x5880_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5809 = CounterChain.copy(x5953, "x5809")
      val x5861 = CounterChain.copy(x5905, "x5861")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5809(0)), CU.ctr(stage(0), x5861(0))), op=FixAdd, results=List(CU.temp(stage(1), tr274)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr274), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr276)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr276), CU.ctr(stage(2), x5806(1))), op=FixAdd, results=List(CU.temp(stage(3), tr277)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr277), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr279)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr277), CU.temp(stage(4), tr279)), op=FixSub, results=List(CU.scalarOut(stage(5), x5884_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr279), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr284)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr284), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr286)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr284), CU.temp(stage(7), tr286)), op=FixSub, results=List(CU.temp(stage(8), tr287)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr286), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr289)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr289), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr290)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr287), CU.temp(stage(10), tr290)), op=FixAdd, results=List(CU.scalarOut(stage(11), x5884_mc.len)))
    }
    val x5951 = MetaPipeline(name = "x5951", parent=x5953, deps=List(x5858, x5905)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr14 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x5912 = CounterChain(name = "x5912", ctr13, ctr14)
      val ctr11 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x5909 = CounterChain(name = "x5909", ctr11)
      var stage: List[Stage] = Nil
    }
    val x5932_0 = Pipeline(name = "x5932_0", parent=x5951, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr312 = CU.temp
      val tr308 = CU.temp
      val x5909 = CounterChain.copy(x5951, "x5909")
      val x5912 = CounterChain.copy(x5951, "x5912")
      val ctr17 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr18 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x5916 = CounterChain(name = "x5916", ctr17, ctr18)
      val x5810_x5919 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5912(0))).wtPort(x5837_mc.vdata)
      val x5811_x5922 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5912(0))).wtPort(x5884_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5916(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr308)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr308), CU.ctr(stage(1), x5909(0))), op=FixAdd, results=List(x5810_x5919.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5909(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr312)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr312), CU.ctr(stage(3), x5916(1))), op=FixAdd, results=List(x5811_x5922.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x5810_x5919), x5811_x5922.load), op=FixMul, results=List(CU.vecOut(stage(5), x5913_vector)))
    }
    val x5949_0 = Pipeline(name = "x5949_0", parent=x5951, deps=List(x5932_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr341 = CU.temp
      val tr330 = CU.temp
      val tr326 = CU.temp
      val tr324 = CU.temp
      val tr323 = CU.temp
      val x5916 = CounterChain.copy(x5932_0, "x5916")
      val ctr23 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr24 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x5912 = CounterChain(name = "x5912", ctr23, ctr24)
      val x5913_x5935 = SRAM(size = 768, writeCtr = x5916(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5912(0), swapWrite = x5916(0))).wtPort(x5913_vector)
      val x5807_x5938 = SRAM(size = 768, writeCtr = x5912(0), banking = Strided(1), buffering = SingleBuffer())
      val wr343 = CU.wtAddr(x5807_x5938)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5913_x5935))
      Stage(stage(1), operands=List(x5916(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr323)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr323), CU.ctr(stage(1), x5916(1))), op=FixAdd, results=List(x5913_x5935.writeAddr, CU.temp(stage(2), tr324)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5912(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr326)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr326), CU.ctr(stage(1), x5912(1))), op=FixAdd, results=List(x5913_x5935.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5912(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr330)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr330), CU.ctr(stage(3), x5912(1))), op=FixAdd, results=List(x5807_x5938.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x5913_x5935), x5807_x5938.load), op=FixAdd, results=List(CU.vecOut(stage(5), x5807_vector), CU.store(stage(5), x5807_x5938)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x5912(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr341)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr341), CU.ctr(stage(6), x5912(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr343)))
    }
    val x5975 = StreamController(name = "x5975", parent=x5977, deps=List(x5953)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr25 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x5956 = CounterChain(name = "x5956", ctr25)
      var stage: List[Stage] = Nil
    }
    val x5960_0 = UnitPipeline(name = "x5960_0", parent=x5975, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr348 = CU.temp
      val tr346 = CU.temp
      val x5806 = CounterChain.copy(x5977, "x5806")
      val x5956 = CounterChain.copy(x5975, "x5956")
      val x5960_unitcc = CounterChain(name = "x5960_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5806(0)), CU.ctr(stage(0), x5956(0))), op=FixAdd, results=List(CU.temp(stage(1), tr346)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr346), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr348)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr348), CU.ctr(stage(2), x5806(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x5973_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x5973_mc.len)))
    }
    val x5971_0 = Pipeline(name = "x5971_0", parent=x5975, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr355 = CU.temp
      val tr353 = CU.temp
      val tr352 = CU.temp
      val x5956 = CounterChain.copy(x5975, "x5956")
      val ctr27 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x5962 = CounterChain(name = "x5962", ctr27)
      val x5912 = CounterChain.copy(x5951, "x5912")
      val x5809 = CounterChain.copy(x5953, "x5809")
      val x5807_x5965 = SRAM(size = 768, writeCtr = x5912(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5956(0), swapWrite = x5809(0))).wtPort(x5807_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5807_x5965))
      Stage(stage(1), operands=List(x5912(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr352)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr352), CU.ctr(stage(1), x5912(1))), op=FixAdd, results=List(x5807_x5965.writeAddr, CU.temp(stage(2), tr353)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5956(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr355)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr355), CU.ctr(stage(1), x5962(0))), op=FixAdd, results=List(x5807_x5965.readAddr))
      Stage(stage(3), operands=List(x5807_x5965.load), op=Bypass, results=List(CU.vecOut(stage(3), x5973_mc.vdata)))
    }
    
  }
}
