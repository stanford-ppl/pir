import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SimpleSequential extends PIRApp {
  def main(top:Top) = {
    val x347_x355_x357_v = Vector("x347_x355_x357")
    val x342_argin = ArgIn("x342").bound(10)
    val x347_x352_v = Vector("x347_x352")
    val x344_x356_argout = ArgOut("x344_x356")
    val x343_argin = ArgIn("x343").bound(2)
    val x358 = Sequential(name="x358",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x358_unit = CounterChain(name = "x358_unit", ctr1)
    }
    val x347_dsp0 = MemoryPipeline(name="x347_dsp0",parent="x358") { implicit CU => 
      val x343_x354 =  ScalarBuffer().wtPort(x343_argin)
      val x352_x352 =  VectorFIFO(size=1).wtPort(x347_x352_v)
      val x349 = CounterChain.copy("x353", "x349")
      val x347_x355 =  SRAM(size=64,banking = NoBanking()).wtPort(x352_x352.readPort).rdPort(x347_x355_x357_v).wtAddr(x349(0))
      var stage: List[Stage] = Nil
      RAStage(operands=List(CU.load(x343_x354)), op=Bypass, results=List(x347_x355.readAddr))
    }
    val x353 = Pipeline(name="x353",parent=x358) { implicit CU => 
      val x342_x350 =  ScalarBuffer().wtPort(x342_argin)
      val ctr2 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x349 = CounterChain(name = "x349", ctr2)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x342_x350), CU.ctr(x349(0))), op=FixMul, results=List(CU.vecOut(x347_x352_v)))
    }
    val x357 = Pipeline(name="x357",parent=x358) { implicit CU => 
      val x343_x354 =  ScalarBuffer().wtPort(x343_argin)
      val x347_x355 =  VectorFIFO(size=1).wtPort(x347_x355_x357_v)
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x357_unit = CounterChain(name = "x357_unit", ctr3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x347_x355)), op=Bypass, results=List(CU.scalarOut(x344_x356_argout)))
    }
    
  }
}
