import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x691_oc = OffChip("x691")
    val x702_0_rd_vector = Vector("x702_0_rd")
    val x694_argout = ArgOut("x694")
    val x734_scalar = Scalar("x734")
    val x693_oc = OffChip("x693")
    val x701_0_rd_vector = Vector("x701_0_rd")
    val x688_argin = ArgIn("x688")
    val x724_mc = MemoryController(TileLoad, x693_oc).parent("x732")
    val x709_mc = MemoryController(TileLoad, x691_oc).parent("x717")
    val x755 = Sequential(name = "x755", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x755_unitcc = CounterChain(name = "x755_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x751 = MetaPipeline(name = "x751", parent=x755) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x688_argin), Const("640i").out) // Counter
      val x700 = CounterChain(name = "x700", ctr1)
      var stage: List[Stage] = Nil
    }
    val x701_dsp0 = MemoryPipeline(name = "x701_dsp0", parent=x751) { implicit CU => 
      val stage0 = CU.emptyStage
      val x736 = CounterChain.copy("x745", "x736")
      val x701_x737 = SemiFIFO(size = 640, banking = Strided(1)).wtPort(x709_mc.data).rdPort(x701_0_rd_vector).rdAddr(x736(0))
      var stage: List[Stage] = Nil
    }
    val x702_dsp0 = MemoryPipeline(name = "x702_dsp0", parent=x751) { implicit CU => 
      val stage0 = CU.emptyStage
      val x736 = CounterChain.copy("x745", "x736")
      val x702_x738 = SemiFIFO(size = 640, banking = Strided(1)).wtPort(x724_mc.data).rdPort(x702_0_rd_vector).rdAddr(x736(0))
      var stage: List[Stage] = Nil
    }
    val x717 = StreamController(name = "x717", parent=x751) { implicit CU => 
      val stage0 = CU.emptyStage
      val x717_unitcc = CounterChain(name = "x717_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x706 = StreamPipeline(name = "x706", parent=x717) { implicit CU => 
      val stage0 = CU.emptyStage
      val x706_unitcc = CounterChain(name = "x706_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x700 = CounterChain.copy("x751", "x700")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x700(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x709_mc.ofs)))
      Stage(stage(2), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(2), x709_mc.len)))
    }
    val x732 = StreamController(name = "x732", parent=x751) { implicit CU => 
      val stage0 = CU.emptyStage
      val x732_unitcc = CounterChain(name = "x732_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x721 = StreamPipeline(name = "x721", parent=x732) { implicit CU => 
      val stage0 = CU.emptyStage
      val x721_unitcc = CounterChain(name = "x721_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x700 = CounterChain.copy("x751", "x700")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x700(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x724_mc.ofs)))
      Stage(stage(2), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(2), x724_mc.len)))
    }
    val x745 = Pipeline(name = "x745", parent=x751) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr54 = CU.temp
      val ctr4 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x736 = CounterChain(name = "x736", ctr4)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x701_0_rd_vector), CU.vecIn(stage(0), x702_0_rd_vector)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr54)))
      val (rs1, rr57) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr57), op=Bypass, results=List(CU.scalarOut(stage(2), x734_scalar)))
    }
    val x750 = UnitPipeline(name = "x750", parent=x751) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar61 = CU.accum(init = Const("0i"))
      val x750_unitcc = CounterChain(name = "x750_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x734_scalar)), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr64) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(CU.accum(stage(2), ar61)), op=Bypass, results=List(CU.scalarOut(stage(2), x694_argout)))
    }
    
  }
}
