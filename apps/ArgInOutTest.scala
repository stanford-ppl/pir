import dhdl.graph._
import dhdl.codegen._
import dhdl.Design

object ArgInOutTest extends Design {

  def main(args: String*) = {
//    if (args.size != 2) {
//      println("\nUsage: ArgInOutTest <#argsIn> <#argsOut>")
//      sys.exit(0)
//    }

    // Read two arguments in (ArgIn) - one integer, one float
    //val a = ArgIn(UInt())("a")
    //val b = ArgIn(UInt())("b")

    // Perform +, *, / on the arguments, store result in an ArgOut
		//val addNode = Add(a,b)
		//val mulNode = Mul(a,b)
		//val divNode = Div(a,b)
		//val absNode = Abs(a)
		//val expNode = Exp(a)
		//val logNode = Log(a)
		//val sqrtNode = Sqrt(Fix2Float(a))
    //val sum = ArgOut("sum", addNode)
    //val mul = ArgOut("mul", mulNode)
    //val div = ArgOut("div", divNode)
//  //  val abs = ArgOut("abs", absNode)
    //val exp = ArgOut("exp", expNode)
    //val log = ArgOut("log", logNode)
    //val sqrt = ArgOut("sqrt", sqrtNode)

    //Wire everything up as a Pipeline
//    val top = Pipe(sum,mul,div,abs,exp,log,sqrt)
    //val top = Pipe(sum,mul,div, exp,log,sqrt)
    SRAM(List(3))
  }

}


