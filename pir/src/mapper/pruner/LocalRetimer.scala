package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._

trait LocalRetimer extends PIRTransformer {

  private val traversal = new PIRTraversal with BFSTopologicalTraversal with UnitTraversal { 
    val forward = true
    override def visitNode(n:N, prev:Unit) = {
      val deps = visitIn(n).filter(withinScope)
      val depDelays = deps.map { dep => dep.delay.get }
      val maxDelay = depDelays.maxOption
      val delay = maxDelay.map { max => max + depDelays.size }.getOrElse(1)
      n.delay := delay
    }
  }

  def retime(ctx:Context, scope:List[PIRNode]):List[Delay] = 
    if (!config.enableRetiming) Nil else dbgblk(s"retime($ctx)"){
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
