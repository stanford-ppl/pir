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
object StreamPipe extends PIRApp {
  override val arch = SN_4x4 

  def main(args: String*)(top:Top) = {
    val tileSize = Const("4i")
    val dataSize = Const("8i")
    val output = ArgOut()
    val bound = ArgIn()
    val vec0 = Vector("vec0")

    // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
    val outer = StreamController(name="outer", parent=top, deps=Nil){ implicit CU =>
      CounterChain(name="i", dataSize by tileSize)
    }
    //Pipe.reduce(tileSize par innerPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
    val inner = StreamPipeline(name="inner", parent=outer, deps=Nil) { implicit CU =>
      // StateMachines / CounterChain
      val ii = CounterChain.copy(outer, "i")
      val et = CU.emptyStage
      val s0::_ = Stages(1)
      // SRAMs
      // Pipeline Stages 
      Stage(s0, op1=CU.ctr(et, ii(0)), op=Bypass, result=CU.vecOut(s0, vec0))
    }
    StreamPipeline (name="accum", parent=outer, deps=List(inner)) { implicit CU =>
      val sA = FIFO(name="sA", size=32, banking=Strided(1), buffering=SingleBuffer()).wtPort(vec0)
      val es = CU.emptyStage
      val s0::_ = Stages(1)
      Stage(s0, op1=sA.load, op=Bypass, CU.scalarOut(s0, output))
    }

  }

}
