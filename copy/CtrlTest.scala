import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.PIRMisc._

/* Example PIR using block (User facing PIR)*/
object CtrlTest extends PIRApp {
  def main(args: String*)(top:Top) = {

    val m0 = ComputeUnit(name="m0", parent=top, tpe=MetaPipeline, deps=Nil){ implicit CU =>
      CounterChain(name="im0", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val s00 = ComputeUnit(name="s00", parent=m0, tpe=Sequential, deps=Nil){ implicit CU =>
      CounterChain(name="s00", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val m01 = ComputeUnit(name="m01", parent=m0, tpe=MetaPipeline, deps=s00::Nil){ implicit CU =>
      CounterChain(name="m01", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p1 = ComputeUnit(name="p1", parent=s00, tpe=Pipe, deps=Nil){ implicit CU =>
      CounterChain(name="p1", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p2 = ComputeUnit(name="p2", parent=s00, tpe=Pipe, deps=p1::Nil){ implicit CU =>
      CounterChain(name="p2", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p3 = ComputeUnit(name="p3", parent=s00, tpe=Pipe, deps=p2::Nil){ implicit CU =>
      CounterChain(name="p3", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p4 = ComputeUnit(name="p4", parent=m01, tpe=Pipe, deps=Nil){ implicit CU =>
      CounterChain(name="p4", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p5 = ComputeUnit(name="p5", parent=m01, tpe=Pipe, deps=p4::Nil){ implicit CU =>
      CounterChain(name="p5", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p7 = ComputeUnit(name="p7", parent=m01, tpe=Pipe, deps=p4::Nil){ implicit CU =>
      CounterChain(name="p6", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
    val p6 = ComputeUnit(name="p6", parent=m01, tpe=Pipe, deps=p5::p7::Nil){ implicit CU =>
      CounterChain(name="p6", Const("0i") by Const("0i"), Const("0i") by Const("0i"))
    }
  }

}
