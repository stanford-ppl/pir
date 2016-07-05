import dhdl.graph._
import dhdl.codegen._
import dhdl.Design

object DotProduct extends Design {

  def main(args: String*) = {
  
    ComputeUnit() {
      //val tileSize = Const(4l)

      // StateMachines / CounterChain
      // val c1 = CounterChain(tileSize by Const(1l)) //Local
      //val c2 = CounterChain.copy(MemCtrl("A"), "cc")
      //val c3 = CounterChain.copy(MemCtrl("B"), "cc")

      // SRAMs
      //val A = SRAM(write=remote, readAddr=c1(0), writeAddr=c2(0))
      //val B = SRAM(write=remote, readAddr=c1(0), writeAddr=c3(0))

      // Pipeline Stages 
      //Pipeline {
        //val s0 = Stage(op1=A.load, op2=B.load, op=Mul, result=PR.reduce)
        //val s1 = Stage.reduce(Add) // Infer log2(par) + 1 stages used for reduction and result stored to PR.reduce
        //val s2 = Stage(op1=PR.reduce, op=Bypass, result=PR.scalarOut(0)) 
      //}
      //Last stage can be removed if PR.reduce and PR.scalarOut map to the same register
    }
  }

}


