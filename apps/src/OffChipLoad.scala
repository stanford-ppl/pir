import pir.graph._
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.misc._
import pir.graph.enums._
import pir.plasticine.config._

/* Examp0ile PIR using block (User facing PIR)*/
object OffChipLoad extends PIRApp {
  //override val arch = P2P_4CU_4TT 
  override val arch = SN_4x4 

  def main(args: String*)(top:Top) = {
    val tileSize = Const("4i")
    val dataSize = ArgIn()
    val output = ArgOut()
    val A = MemoryController("A", TileLoad, OffChip())
    val tlAVec = Vector()
    val innerScalar = Scalar("innerScalar")

    val outer = Sequential(name="outer", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1291_unitCC = CounterChain(name = "x1291_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
    val load = MetaPipeline(name="load", parent=outer, deps=Nil){ implicit CU =>
      val es = CU.emptyStage
      val cc = CounterChain(name="i", CU.scalarIn(es, dataSize) by tileSize)
    }
    // b1 := v1(i::i+tileSize)
    val tileLoadA = TileTransfer(name="tileLoadA", parent=load, memctrl=A, mctpe=TileLoad,
      deps=Nil, vec=tlAVec){ implicit CU =>
      val ic = CounterChain.copy(load, "i")
      val it = CounterChain(name="it", Const("0i") until tileSize by Const("1i"))
      //TODO
      val stream = CounterChain(name="stream", Const("0i") until tileSize by Const("1i"))
      stream.isStreaming(true)
      val s0::_ = Stages(1)
      val es = CU.emptyStage 
      val output = CU.scalarOut(s0, A.ofs)
      Stage(s0, op1=CU.ctr(es, it(0)), op2=CU.ctr(es, ic(0)), op=FixAdd, result=output)
    }
    //Pipe.reduce(tileSize par innerPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
    val inner = Pipeline(name="inner", parent=outer, deps=List(load)) { implicit CU =>
      // StateMachines / CounterChain
      val ii = CounterChain(tileSize by Const("1i")) //Local
      val itA = CounterChain.copy(tileLoadA, "stream")

      val s0::s1::_ = Stages(2)
      // SRAMs
      val sA = SRAM(name="sA", size=32, banking=NoBanking(), buffering=MultiBuffer(depth=2, 
        swapRead=itA(0), swapWrite=itA(0)), writeCtr=itA(0)).wtPort(tlAVec).rdAddr(ii(0)).wtAddr(itA(0))
      // Pipeline Stages 
      Stage(s0, op1=sA.load, op2=sA.load, op=FixMul, result=CU.reduce(s0))
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
