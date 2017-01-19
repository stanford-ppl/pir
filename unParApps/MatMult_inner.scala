import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_innerDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4632_oc = OffChip("x4632")
    val x4634_oc = OffChip("x4634")
    val x4864_scalar = Scalar("x4864")
    val x4633_oc = OffChip("x4633")
    val x4968_scalar = Scalar("x4968")
    val x4941_mc = MemoryController(TileLoad, x4633_oc)
    val x4894_mc = MemoryController(TileLoad, x4632_oc)
    val x5018_mc = MemoryController(TileStore, x4634_oc)
    val x5024 = Sequential(name = "x5024", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5024_unitcc = CounterChain(name = "x5024_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5022 = MetaPipeline(name = "x5022", parent=x5024, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val ctr2 = (Const("0i").out, Const("7680i").out, Const("48i").out) // Counter
      val x4863 = CounterChain(name = "x4863", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x4998 = MetaPipeline(name = "x4998", parent=x5022, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x4866 = CounterChain(name = "x4866", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4915 = StreamController(name = "x4915", parent=x4998, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x4871 = CounterChain(name = "x4871", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4890_0 = UnitPipeline(name = "x4890_0", parent=x4915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr221 = CU.temp
      val tr220 = CU.temp
      val tr218 = CU.temp
      val tr217 = CU.temp
      val tr215 = CU.temp
      val tr210 = CU.temp
      val tr208 = CU.temp
      val tr207 = CU.temp
      val tr205 = CU.temp
      val x4890_unitcc = CounterChain(name = "x4890_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4866 = CounterChain.copy(x4998, "x4866")
      val x4871 = CounterChain.copy(x4915, "x4871")
      val x4863 = CounterChain.copy(x5022, "x4863")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4863(0)), CU.ctr(stage(0), x4871(0))), op=FixAdd, results=List(CU.temp(stage(1), tr205)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr205), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr207)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr207), CU.ctr(stage(2), x4866(0))), op=FixAdd, results=List(CU.temp(stage(3), tr208)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr208), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr210)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr208), CU.temp(stage(4), tr210)), op=FixSub, results=List(CU.scalarOut(stage(5), x4894_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr210), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr215)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr215), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr217)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr215), CU.temp(stage(7), tr217)), op=FixSub, results=List(CU.temp(stage(8), tr218)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr217), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr220)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr220), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr221)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr218), CU.temp(stage(10), tr221)), op=FixAdd, results=List(CU.scalarOut(stage(11), x4894_mc.len)))
    }
    val x4962 = StreamController(name = "x4962", parent=x4998, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4918 = CounterChain(name = "x4918", ctr15)
      var stage: List[Stage] = Nil
    }
    val x4937_0 = UnitPipeline(name = "x4937_0", parent=x4962, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr251 = CU.temp
      val tr250 = CU.temp
      val tr248 = CU.temp
      val tr247 = CU.temp
      val tr245 = CU.temp
      val tr240 = CU.temp
      val tr238 = CU.temp
      val tr237 = CU.temp
      val tr235 = CU.temp
      val x4866 = CounterChain.copy(x4998, "x4866")
      val x4863 = CounterChain.copy(x5022, "x4863")
      val x4937_unitcc = CounterChain(name = "x4937_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4918 = CounterChain.copy(x4962, "x4918")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4866(0)), CU.ctr(stage(0), x4918(0))), op=FixAdd, results=List(CU.temp(stage(1), tr235)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr235), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr237)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr237), CU.ctr(stage(2), x4863(1))), op=FixAdd, results=List(CU.temp(stage(3), tr238)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr238), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr240)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr238), CU.temp(stage(4), tr240)), op=FixSub, results=List(CU.scalarOut(stage(5), x4941_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr240), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr245)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr245), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr247)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr245), CU.temp(stage(7), tr247)), op=FixSub, results=List(CU.temp(stage(8), tr248)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr247), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr250)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr250), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr251)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr248), CU.temp(stage(10), tr251)), op=FixAdd, results=List(CU.scalarOut(stage(11), x4941_mc.len)))
    }
    val x4996 = MetaPipeline(name = "x4996", parent=x4998, deps=List(x4915, x4962)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4967 = CounterChain(name = "x4967", ctr11, ctr12)
      var stage: List[Stage] = Nil
    }
    val x4984_0 = Pipeline(name = "x4984_0", parent=x4996, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr273 = CU.temp
      val tr269 = CU.temp
      val ctr17 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4970 = CounterChain(name = "x4970", ctr17)
      val x4967 = CounterChain.copy(x4996, "x4967")
      val x4867_x4973 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4967(0))).wtPort(x4894_mc.vdata)
      val x4868_x4976 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4967(0))).wtPort(x4941_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4967(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr269)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr269), CU.ctr(stage(1), x4970(0))), op=FixAdd, results=List(x4867_x4973.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4970(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr273)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr273), CU.ctr(stage(3), x4967(1))), op=FixAdd, results=List(x4868_x4976.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x4867_x4973), x4868_x4976.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr280) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr280), op=Bypass, results=List(CU.scalarOut(stage(6), x4968_scalar)))
    }
    val x4994_0 = UnitPipeline(name = "x4994_0", parent=x4996, deps=List(x4984_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr292 = CU.temp
      val tr290 = CU.temp
      val tr289 = CU.temp
      val tr284 = CU.temp
      val x4994_unitcc = CounterChain(name = "x4994_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4866 = CounterChain.copy(x4998, "x4866")
      val x4967 = CounterChain.copy(x4996, "x4967")
      val x4864_x4987 = SRAM(size = 768, writeCtr = x4994_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr294 = CU.wtAddr(x4864_x4987)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4967(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr284)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr284), CU.ctr(stage(1), x4967(1))), op=FixAdd, results=List(x4864_x4987.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4866(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr289)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr289), Const("0i"), CU.load(stage(3), x4864_x4987)), op=Mux, results=List(CU.temp(stage(4), tr290)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr290), CU.scalarIn(stage(4), x4968_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x4864_scalar), CU.store(stage(5), x4864_x4987)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x4967(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr292)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr292), CU.ctr(stage(6), x4967(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr294)))
    }
    val x5020 = StreamController(name = "x5020", parent=x5022, deps=List(x4998)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr21 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x5001 = CounterChain(name = "x5001", ctr21)
      var stage: List[Stage] = Nil
    }
    val x5005_0 = UnitPipeline(name = "x5005_0", parent=x5020, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr299 = CU.temp
      val tr297 = CU.temp
      val x4863 = CounterChain.copy(x5022, "x4863")
      val x5001 = CounterChain.copy(x5020, "x5001")
      val x5005_unitcc = CounterChain(name = "x5005_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4863(0)), CU.ctr(stage(0), x5001(0))), op=FixAdd, results=List(CU.temp(stage(1), tr297)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr297), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr299)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr299), CU.ctr(stage(2), x4863(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x5018_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x5018_mc.len)))
    }
    val x5016_0 = Pipeline(name = "x5016_0", parent=x5020, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr306 = CU.temp
      val tr304 = CU.temp
      val tr303 = CU.temp
      val x4994_unitcc = CounterChain.copy(x4994_0, "x4994_unitcc")
      val x5001 = CounterChain.copy(x5020, "x5001")
      val ctr23 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x5007 = CounterChain(name = "x5007", ctr23)
      val x4866 = CounterChain.copy(x4998, "x4866")
      val x4967 = CounterChain.copy(x4996, "x4967")
      val x4864_x5010 = SRAM(size = 768, writeCtr = x4994_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5001(0), swapWrite = x4866(0))).wtPort(x4864_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4864_x5010))
      Stage(stage(1), operands=List(x4967(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr303)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr303), CU.ctr(stage(1), x4967(1))), op=FixAdd, results=List(x4864_x5010.writeAddr, CU.temp(stage(2), tr304)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5001(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr306)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr306), CU.ctr(stage(1), x5007(0))), op=FixAdd, results=List(x4864_x5010.readAddr))
      Stage(stage(3), operands=List(x4864_x5010.load), op=Bypass, results=List(CU.vecOut(stage(3), x5018_mc.vdata)))
    }
    
  }
}
