import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._

/* Example PIR without using block (Spatial to PIR generation)*/
object DotProductNoSugar extends Design {
  override val arch = Config0 

  val tileSize = Const(4l)
  val dataSize = ArgIn()
  val output = ArgOut()
  val A = MemoryController("A")
  val B = MemoryController("B")
  val tileA = Vector("tileA")
  val tileB = Vector("tileB")

  // Pipe.fold(dataSize by tileSize par outerPar)(out){ i =>
  val outer = {
    implicit val CU = ComputeUnit(name=None, tpe=MetaPipeline).updateParent("Top")
    val ds = ScalarIn(dataSize)
    CU.updateFields(
      cchains=List(CounterChain(name="i", ds.out by tileSize)),
      srams=Nil,
      sins=List(ds),
      souts=Nil,
      vins=Nil,
      vouts=Nil
    )
  }
  // b1 := v1(i::i+tileSize)
  val tileLoadA =  {
    implicit val CU = TileTransfer (name=None, oc=A, mt=TileLoad).updateParent(outer)
    val ic = CounterChain.copy(outer, "i")
    val it = CounterChain(name="it", Const(0) until tileSize by Const(1))
    val s0::_ = Stages(1)
    Stage(s0, op1=it(0), op2=ic(0), op=FixAdd, result=CU.scalarOut(s0, A.readAddr))
    CU.updateFields(
      cchains=List(ic, it),
      srams=Nil,
      sins=Nil,
      souts=Nil,
      vins=List(VecIn(CU.name, tileA)), 
      vouts=Nil
    )
  }
  // b2 := v2(i::i+tileSize)
  val tileLoadB =  {
    implicit val CU = TileTransfer (name=None, oc=A, mt=TileLoad).updateParent(outer)
    val ic = CounterChain.copy(outer, "i")
    val it = CounterChain(name="it", Const(0) until tileSize by Const(1))
    val s0::_ = Stages(1)
    Stage(s0, op1=it(0), op2=ic(0), op=FixAdd, result=CU.scalarOut(s0, B.readAddr))
    CU.updateFields(
      cchains=List(ic, it),
      srams=Nil,
      sins=Nil,
      souts=Nil,
      vins=List(VecIn(CU.name, tileB)),
      vouts=Nil
    )
  }
  //Pipe.reduce(tileSize par innerPar)(Reg[T]){ii => b1(ii) * b2(ii) }{_+_}
  val inner = {
    implicit val CU = ComputeUnit(name=Some("inner"), tpe=Pipe).updateParent(outer)
    // StateMachines / CounterChain
    val ii = CounterChain(tileSize by Const(1l)) //Local
    val itA = CounterChain.copy(tileLoadA, "it")
    val itB = CounterChain.copy(tileLoadB, "it")
    val s0::s1::s2::_ = Stages(3)
    // SRAMs
    val sA = SRAM(size=32, vec=A.load, readAddr=ii(0), writeAddr=itA(0))
    val sB = SRAM(size=32, vec=A.load, readAddr=ii(0), writeAddr=itB(0))
    //Scalar buffer
    val out = ScalarOut(output)
    // Pipeline Stages 
    Stage(s0, opds=List(sA.load,sB.load), o=FixMul, r=CU.reduce(s0))
    Stage.reduce(s1, op=FixAdd) 
    Stage(s2, opds=List(CU.reduce(s1)), o=Bypass, r=CU.scalarOut(s0, out))

    CU.updateFields(
      cchains=List(ii, itA, itB),
      srams=Nil,
      sins=Nil,
      souts=List(out),
      vins=List(VecIn(A.load), VecIn(tileB)),
      vouts=Nil
    )
  }

  top = Top().updateFields(List(outer, tileLoadA, tileLoadB, inner), 
                           List(dataSize, output), 
                           List(A, B))

  def main(args: Array[String]): Unit = {
    run
  }
}
