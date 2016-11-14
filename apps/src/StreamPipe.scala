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

  def main(args: String*)(top:Top) = {
    val tileSize = Const("4i")
    val dataSize = Const("8i")
    val output = ArgOut()
    val vecA = Vector("vecA")
    val vecB = Vector("vecB")

    // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
    val outer = StreamController(name="outer", parent=top, deps=Nil){ implicit CU =>
      CounterChain(name="i", dataSize by tileSize)
    }
    //Pipe.reduce(tileSize par cuAPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
    val cuA = StreamPipeline(name="cuA", parent=outer, deps=Nil) { implicit CU =>
      // StateMachines / CounterChain
      val ii = CounterChain.copy(outer, "i")
      val et = CU.emptyStage
      val s0::_ = Stages(1)
      // SRAMs
      // Pipeline Stages 
      Stage(s0, op1=CU.ctr(et, ii(0)), op=Bypass, result=CU.vecOut(s0, vecA))
    }
    val cuB = StreamPipeline(name="cuB", parent=outer, deps=Nil) { implicit CU =>
      // StateMachines / CounterChain
      val ii = CounterChain.copy(outer, "i")
      val et = CU.emptyStage
      val s0::_ = Stages(1)
      // SRAMs
      // Pipeline Stages 
      Stage(s0, op1=CU.ctr(et, ii(0)), op=Bypass, result=CU.vecOut(s0, vecB))
    }
    StreamPipeline (name="accum", parent=outer, deps=List(cuA, cuB)) { implicit CU =>
      val sA = FIFO(name="sA", size=32, banking=Strided(1)).wtPort(vecA)
      val sB = FIFO(name="sB", size=32, banking=Strided(1)).wtPort(vecB)
      val es = CU.emptyStage
      val s0::_ = Stages(1)
      Stage(s0, op1=sA.load, op2=sB.load, op=Bypass, CU.scalarOut(s0, output))
    }

  }

}
