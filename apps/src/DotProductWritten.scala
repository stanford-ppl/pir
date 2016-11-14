import pir.graph._
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.misc._
import pir.graph.enums._
import pir.plasticine.config._

/* Examp0ile PIR using block (User facing PIR)*/
object DotProduct extends PIRApp {
  //override val arch = P2P_4CU_4TT 
  override val arch = SN_2x2 

  def main(args: String*)(top:Top) = {
    val tileSize = Const("4i")
    val dataSize = ArgIn()
    val output = ArgOut()
    val A = MemoryController("A", TileLoad, OffChip())
    val B = MemoryController("B", TileLoad, OffChip())
    val innerScalar = Scalar("innerScalar")

    // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
    val outer = MetaPipeline(name="outer", parent=top, deps=Nil){ implicit CU =>
      val es = CU.emptyStage
      val cc = CounterChain(name="i", CU.scalarIn(es, dataSize) by tileSize)
    }
    val tileLoadA = StreamController(name="tileLoadA", parent=outer, deps=Nil){ implicit CU =>
      val es = CU.emptyStage
      val cc = CounterChain(name="i", CU.scalarIn(es, dataSize) by tileSize)
    }
    // b1 := v1(i::i+tileSize)
    val addrCalcA = UnitPipeline(name="addrCalcA", parent=tileLoadA, deps=Nil){ implicit CU =>
      val ic = CounterChain.copy(outer, "i")
      val it = CounterChain(name="it", Const("0i") until tileSize by Const("1i"))
      val s0::s1::_ = Stages(2)
      val es = CU.emptyStage 
      Stage(s0, op1=CU.ctr(es, it(0)), op2=CU.ctr(es, ic(0)), op=FixAdd, result=CU.scalarOut(s0, A.saddr))
      Stage(s1, op1=tileSize, op=Bypass, result=CU.scalarOut(s1, A.ssize))
    }
    val tileLoadB = StreamController(name="tileLoadB", parent=outer, deps=Nil){ implicit CU =>
      val es = CU.emptyStage
      val cc = CounterChain(name="i", CU.scalarIn(es, dataSize) by tileSize)
    }
    // b2 := v2(i::i+tileSize)
    val addrCalcB = UnitPipeline(name="addrCalcB", parent=tileLoadB, deps=Nil){ implicit CU =>
      val ic = CounterChain.copy(outer, "i")
      val it = CounterChain(name="it", Const("0i") until tileSize by Const("0i"))
      val s0::s1::_ = Stages(2)
      val es = CU.emptyStage 
      Stage(s0, op1=CU.ctr(es, it(0)), op2=CU.ctr(es, ic(0)), op=FixAdd, result=CU.scalarOut(s0, B.saddr))
      Stage(s1, op1=tileSize, op=Bypass, result=CU.scalarOut(s1, B.ssize))
    }
    //Pipe.reduce(tileSize par innerPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
    val inner = Pipeline(name="inner", parent=outer, deps=List(tileLoadA, tileLoadB)) { implicit CU =>
      // StateMachines / CounterChain
      val ii = CounterChain(tileSize by Const("1i")) //Local

      val s0::s1::_ = Stages(2)
      // SRAMs
      // TODO
      val sA = SemiFIFO(name="sA", size=32, banking=NoBanking(), buffering=SingleBuffer()).wtPort(A.vdata).rdAddr(ii(0))
      val sB = SemiFIFO(name="sB", size=32, banking=NoBanking(), buffering=SingleBuffer()).wtPort(B.vdata).rdAddr(ii(0))
      // Pipeline Stages 
      Stage(s0, op1=sA.load, op2=sB.load, op=FixMul, result=CU.reduce(s0))
      val (sr, acc) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(s1, op1=acc, op=Bypass, result=CU.scalarOut(s1, innerScalar))
      //Last stage can be removed if CU.reduce and CU.scalarOut map to the same register
    }
    UnitPipeline (name="accum", parent=outer, deps=List(inner)) { implicit CU =>
      CounterChain(Const("1i") by Const("1i")) //Local
      val ic = CounterChain.copy(outer, "i")
      val es = CU.emptyStage
      val s0::s1::_ = Stages(2)
      val accum = CU.accum(s0, init=Const("0i"))
      Stage(s0, op1=CU.scalarIn(es, innerScalar), op2=accum, op=FixAdd, accum)
      Stage(s1, op1=accum, op=Bypass, result=CU.scalarOut(s1, output))
    }

  }

}
