import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.PIRMisc._

/* Example PIR using block (User facing PIR)*/
object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val tileSize = Const(4l)
    val dataSize = ArgIn()
    val output = ArgOut()
    val A = MemoryController("A", TileLoad, OffChip())
    val B = MemoryController("B", TileLoad, OffChip())

    // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
    val outer = ComputeUnit(name="outer", parent=top, tpe=MetaPipeline, deps=List("inner")){ implicit CU =>
      CounterChain(name="i", CU.scalarIn(dataSize) by tileSize)
    }
    // b1 := v1(i::i+tileSize)
    val tileLoadA = TileTransfer(name="tileLoadA", parent=outer, memctrl=A, mctpe=TileLoad, deps=List("inner")){ implicit CU =>
      val ic = CounterChain.copy(outer, "i")
      val it = CounterChain(name="it", Const(0) until tileSize by Const(1))
      val s0::_ = Stages(1)
      Stage(s0, op1=it(0), op2=ic(0), op=FixAdd, result=CU.scalarOut(s0, A.saddr))
    }
    // b2 := v2(i::i+tileSize)
    val tileLoadB = TileTransfer(name="tileLoadB", parent=outer, memctrl=B, mctpe=TileLoad, deps=List("inner")){ implicit CU =>
      val ic = CounterChain.copy(outer, "i")
      val it = CounterChain(name="it", Const(0) until tileSize by Const(1))
      val s0::_ = Stages(1)
      Stage(s0, op1=it(0), op2=ic(0), op=FixAdd, result=CU.scalarOut(s0, B.saddr))
    }
    //Pipe.reduce(tileSize par innerPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
    ComputeUnit (name="inner", parent=outer, tpe=Pipe, deps=List(tileLoadA, tileLoadB)) { implicit CU =>
      
      // StateMachines / CounterChain
      val ii = CounterChain(tileSize by Const(1l)) //Local
      val itA = CounterChain.copy(tileLoadA, "it")
      val itB = CounterChain.copy(tileLoadB, "it")

      //val waStages = WAStages(1)
      //val raStages = RAStages(1)

      //val ra = CU.rdAddr() 
      //val wa = CU.wtAddr()
      //val wp = CU.store()
      //val wp = CU.vecIn(tileLoad.out)

      //val sA = SRAM(size=32, writePort=CU.store(sx, wp), 
      //                       readAddr=CU.wtAddr(st, ra),
      //                       writeAddr=CU.rdAddr(st, wa))

      val s0::s1::_ = Stages(2)
      // SRAMs
      val sA = SRAM(size=32, vec=tileLoadA.out, readAddr=ii(0), writeAddr=itA(0))
      val sB = SRAM(size=32, vec=tileLoadB.out, readAddr=ii(0), writeAddr=itB(0))
      // ScalarBuffers
      val out = ScalarOut(output)

      // Pipeline Stages 
      Stage(s0, op1=sA.load, op2=sB.load, op=FixMul, result=CU.reduce(s0))
      val (sr, acc) = Stage.reduce(op=FixAdd, init=Const(0l))
      Stage(s1, op1=acc, op=Bypass, result=CU.scalarOut(s1, out))
      //Last stage can be removed if CU.reduce and CU.scalarOut map to the same register
    }
  }

}
