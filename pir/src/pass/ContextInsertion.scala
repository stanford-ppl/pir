package pir
package pass

import pir.node._
import scala.collection.mutable

class ContextInsertion(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def runPass =  {
    designP.top.argFringe.tokenInDef //HACK: enforce lazy value instantiation
    super.runPass
  }

  override def visitNode(n:N) = n match {
    case n:GlobalContainer => insertContext(n)
    case n => super.visitNode(n)
  }

  val schedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = ContextInsertion.this.logger
    val forward = false
    override def visitIn(n:N):List[N] = (n match {
      case n:Memory => Nil
      case n => visitLocalIn(n)
    }).filterNot(_.isInstanceOf[ComputeContext])
    override def visitOut(n:N):List[N] = visitLocalOut(n).filterNot(_.isInstanceOf[ComputeContext])
    override def isDepFree(n:N) = depFunc(n).exists(isVisited)
  }

  def insertContext(n:GlobalContainer):Unit = dbgblk(s"insertContext($n)"){
    val outs = n.children.filter { exp => schedular.depFunc(exp).isEmpty }
    outs.groupBy { out => ctrlOf(out) }.foreach { case (ctrl, outs) =>
      insertContext(n, outs)
    }
  }

  def insertContext(n:GlobalContainer, outs:List[N]):Unit = {
    val exps = schedular.scheduleNodesInScope(n.children, outs)
    dbg(s"outs=$outs exps=$exps")
    val context = ComputeContext().setParent(n)
    val (mems, others) = exps.partition { _.isInstanceOf[Memory] }
    others.foreach { exp =>
      exp.deps.filter { dep => within[ComputeContext](dep) && dep.isDescendentOf(n) }.foreach { dep =>
        val (mdep, ms) = mirrored(dep, Some(context)) 
        swapConnection(exp.asInstanceOf[Def], dep.out, mdep.out)
      }
      swapParent(exp, context)
    }
    mems.flatMap { mem => inAccessesOf(mem.asInstanceOf[Memory]) }.foreach { out =>
      insertContext(n, List(out))
    }
  }

}
