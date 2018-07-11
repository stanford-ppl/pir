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

    // Prevent mirror the writers
    val inAccesses = n.children.collect { case mem:Memory => inAccessesOf(mem) }.flatten.toSeq
    val init = inAccesses.map { a => (a.asInstanceOf[N],a.asInstanceOf[N]) }.toMap

    def insertContext(n:GlobalContainer, outs:List[N]):Unit = {
      val newOuts = outs.filterNot { x => within[ComputeContext](x) }
      val exps = schedular.scheduleNodesInScope(n.children, newOuts)
      val (mems, others) = exps.partition { _.isInstanceOf[Memory] }
      dbg(s"outs=$outs exps=$exps")
      val context = ComputeContext().setParent(n)
      dbgblk(s"insertContext $context") {
        var toCtx = others
        val mapping = mutable.Map[N,N]() ++ init
        others.foreach { exp =>
          exp.deps.filter { dep => within[ComputeContext](dep) && dep.isDescendentOf(n) }.foreach { dep =>
            dbg(s"dep=$dep in ${contextOf(dep).get}")
            val (mdep, ms) = mirrored(dep, mapping=mapping, container=Some(n)) 
            swapConnection(exp.asInstanceOf[Def], dep.out, mdep.out)
            toCtx ++= ms.filter { 
              case m:Memory => false
              case m if m.parent == Some(n) => true
              case m => false
            }
          }
        }
        toCtx.foreach { exp => swapParent(exp, context) }
      }
      mems.flatMap { mem => inAccessesOf(mem.asInstanceOf[Memory]) }.foreach { out =>
        insertContext(n, List(out))
      }
    }

    val outs = n.children.filter { exp => 
      val isOutput = exp.depeds.exists { !_.isDescendentOf(n) }
      val noDependency = exp.depeds.isEmpty
      isOutput || noDependency
    }
    outs.groupBy { out => ctrlOf(out) }.foreach { case (ctrl, outs) =>
      insertContext(n, outs)
    }
  }


}
