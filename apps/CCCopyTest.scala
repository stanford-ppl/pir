import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.PIRMisc._

/* Example PIR using block (User facing PIR)*/
object CCCopyTest extends PIRApp {
  def main(args: String*)(top:Top) = {

    val m0 = ComputeUnit(name="m0", parent=top, tpe=MetaPipeline, deps=Nil){ implicit CU =>
      CounterChain(name="im0", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val s00 = ComputeUnit(name="s00", parent=m0, tpe=Sequential, deps=Nil){ implicit CU =>
      CounterChain(name="is00", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val m01 = ComputeUnit(name="m01", parent=m0, tpe=MetaPipeline, deps=s00::Nil){ implicit CU =>
      CounterChain(name="im01", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p1 = ComputeUnit(name="p1", parent=s00, tpe=Pipe, deps=Nil){ implicit CU =>
      CounterChain(name="ip1", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p2 = ComputeUnit(name="p2", parent=s00, tpe=Pipe, deps=p1::Nil){ implicit CU =>
      CounterChain(name="ip2", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p3 = ComputeUnit(name="p3", parent=s00, tpe=Pipe, deps=p2::Nil){ implicit CU =>
      CounterChain(name="ip3", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p4 = ComputeUnit(name="p4", parent=m01, tpe=Pipe, deps=Nil){ implicit CU =>
      CounterChain(name="ip4", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
      CounterChain.copy("m0", "im0")
    }
    val p5 = ComputeUnit(name="p5", parent=m01, tpe=Pipe, deps=p4::Nil){ implicit CU =>
      CounterChain(name="ip5", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p6 = ComputeUnit(name="p6", parent=m01, tpe=Pipe, deps=p5::Nil){ implicit CU =>
      CounterChain(name="ip6", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
  }

}
