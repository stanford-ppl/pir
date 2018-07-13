package pir
package pass

import pir.node._
import scala.collection.mutable

class ConstantExpressionEvaluation(implicit compiler:PIR) extends PIRTransformer with BFSBottomUpTopologicalTraversal with UnitTraversal with ConstantPropogator {
  import pirmeta._

  val forward = true

  override def visitNode(n:N, prev:T):T = dbgblk(s"visit ${qdef(n)}") {
    n match {
      case Def(n:OpDef, OpDef(op, inputs)) => 
        constToExp(evalOpt[Any](op, inputs.map(expToOpt))).fold {
          super.visitNode(n, prev)
        } { exp =>
          dbg(s"evaluate ${qdef(n)} -> ${qdef(exp)}")
          swapUsage[Def](n, exp)
          super.visitNode(exp, prev)
        }
      case _ => super.visitNode(n, prev)
    }
  }

  def expToOpt(x:N):Any = x match {
    case Const(x) => Some(x)
    case x => x
  }

  def constToExp(x:Any):Option[Def] = x match {
    case Some(x) => Some(Const(x))
    case x:Def => Some(x)
    case None => None
  }

}

