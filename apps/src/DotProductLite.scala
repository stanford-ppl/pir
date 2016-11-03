import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.misc._
import pir.graph.enums._
import pir.plasticine.config._

/* Examp0ile PIR using block (User facing PIR)*/
object DotProductLite extends PIRApp {
  override val arch = SN_4x4 

  def main(args: String*)(top:Top) = {
    val tileSize = Const("4i")
    val dataSize = Const("8i")
    val output = ArgOut()
    val innerScalar = Scalar("innerScalar")

    // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
    val outer = MetaPipeline(name="outer", parent=top, deps=Nil){ implicit CU =>
      CounterChain(name="i", dataSize by tileSize)
    }
    //Pipe.reduce(tileSize par innerPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
    val inner = Pipeline(name="inner", parent=outer, deps=Nil) { implicit CU =>
      // StateMachines / CounterChain
      val ii = CounterChain(tileSize by Const("1i")) //Local

      val et = CU.emptyStage
      val s0::s1::_ = Stages(2)
      // SRAMs
      // SingleBuffer = no DoubleBuffer
      //val sA = SRAM(name="sA", size=32, readAddr=ii(0), writeAddr=ii(0), 
        //banking=Strided(1), buffering=SingleBuffer(), writeCtr=ii(0))
      //val sB = SRAM(name="sB", size=32, readAddr=ii(0), writeAddr=ii(0),
        //banking=Strided(1), buffering=SingleBuffer(), writeCtr=ii(0))
      // Pipeline Stages 
      Stage(s0, op1=CU.ctr(et, ii(0)), op2=CU.ctr(et, ii(0)), op=FixMul, result=CU.reduce(s0))
      // Writing some random constant to sA and sB locally to avoid no connection to sram write port
      val (sr, acc) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(s1, op1=acc, op=Bypass, result=CU.scalarOut(s1, innerScalar))
      //Last stage can be removed if CU.reduce and CU.scalarOut map to the same register
    }
    UnitPipeline (name="accum", parent=outer, deps=List(inner)) { implicit CU =>
      CounterChain(Const("1i") by Const("1i")) //Local
      val es = CU.emptyStage
      val s0::s1::_ = Stages(2)
      val accum = CU.accum(s0, init=Const("0i"))
      Stage(s0, op1=CU.scalarIn(es, innerScalar), op2=accum, op=FixAdd, accum)
      Stage(s1, op1=accum, op=Bypass, result=CU.scalarOut(s1, output))
    }

  }

}
