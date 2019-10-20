package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import spade.param._

trait LocalRetimer extends PIRTransformer {

  //TODO: this is suboptimum because the analysis is a forward pass. A backward pass like in global
  //retimer will schedule nodes as late as possible, which reduces the amount of live vairables.
  //We assume the # external variables << # internal variables
  private val traversal = new PIRTraversal with BFSTopologicalTraversal with UnitTraversal { 
    val forward = true
    override def visitNode(n:N, prev:Unit) = {
      val deps = visitIn(n).filter(withinScope)
      val depDelays = deps.map { dep => dep.delay.get }
      val maxDelay = depDelays.maxOption
      // Be conservative. Assume nodes before this nodes cannot execute in parallel
      val delay = maxDelay.map { max => max + depDelays.size }.getOrElse(1)
      n.delay := delay
    }
  }

  def retimeLocal(ctx:Context, scope:List[PIRNode]):List[Delay] = 
    if (!config.enableLocalRetiming) Nil else dbgblk(s"retimeLocal($ctx)"){
      traversal.resetTraversal
      traversal.traverseScope(scope, ())
      val scopeSet = scope.toSet
      val fifoDepth = assertUnify(spadeParam.traceIn[FIFOParam], "fifoParam") { _.depth }.get
      val delays = ListBuffer[Delay]()
      scope.foreach { n =>
        n.depsFrom.foreach { case (out, ins) =>
          val dep = out.src.as[PIRNode]
          if (scopeSet.contains(dep) || dep.isInstanceOf[BufferRead]) {
            var diff = n.delay.get - dep.delay.v.getOrElse(0)
            if (diff > fifoDepth) {
              within(ctx, dep.getCtrl) {
                var prev = out
                while (diff > 0) {
                  val delay = stage(Delay(Math.min(fifoDepth, diff)).in(prev))
                  delays += delay
                  prev = delay.out
                  diff -= fifoDepth
                }
                ins.foreach { in =>
                  swapConnection(in, out, prev)
                }
              }
            }
          }
        }
      }
      delays.toList
    }
}
