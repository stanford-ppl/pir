package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import spade.param._

trait LocalRetimer extends PIRTransformer {

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

trait PartitionRetimer extends PIRTransformer {
  private val traversal = new PIRTraversal with BFSTopologicalTraversal with UnitTraversal { 
    val forward = false // Reverse to schedule nodes as late as possible. Assume small fanins from external
    override def visitIn(n:N) = visitLocalIn(n)
    override def visitOut(n:N) = visitLocalOut(n)
    override def visitNode(n:N, prev:Unit) = {
      val depeds = visitOut(n).filter(withinScope)
      val depedDelays = depeds.map { deped => deped.delay.get }
      val delay = depedDelays.minOptionBy { i => i }.map { min => min - numStage }.getOrElse(0)
      n.delay := delay
    }
    var numStage:Int = 0
  }

  def retimePartition(scope:List[Context], numStage:Int):List[Context] = 
    if (!config.enablePartitionRetiming) Nil else dbgblk(s"retimePartition(${scope.head.global.get})"){
      traversal.resetTraversal
      traversal.numStage = numStage
      traversal.traverseScope(scope, ())
      //breakPoint(s"Finish analysis")
      val externDelay = scope.toStream.map { _.delay.get }.min - numStage
      val global = scope.head.global.get
      val fifoDepth = assertUnify(spadeParam.traceIn[FIFOParam], "fifoParam") { _.depth }.get
      within(global) {
        // A map between outputs (internal and external) to internal inputs
        val outIns = scope.map { n => n.depsFrom }.reduce { _ ++ _ }
        // Inserting retiming ops for each output
        val delayOps = outIns.map { case (out, ins) =>
          out -> retimeOutput(out, ins, externDelay, fifoDepth, numStage)
        }.toMap
        // Put retiming ops into retiming contexts. Share context for outputs from the same context
        delayOps.groupBy { case (out, delayOps) => out.src.ctx.get
        }.flatMap { case (dep, group) =>
          val delayOps = group.flatMap { case (out, delayOps) => delayOps }
          val layers = delayOps.groupBy { _.delay.get }.toSeq.sortBy { _._1 }
          val retimeCtxs = layers.map { case (delay, ops) =>
            val retimeCtx = within(dep.getCtrl) { stage(Context().streaming(true)) }
            ops.foreach { op =>
              swapParent(op, retimeCtx)
              op.in.singleConnected.get.src match {
                case write:BufferWrite =>
                  swapConnection(op.in, write.out, write.data.singleConnected.get)
                  bufferInput(op.in).foreach { buffer =>
                    transferLocalAccess(write, buffer.inAccess)
                  }
                case delay:Delay => bufferInput(op.in)
              }
            }
            //breakPoint(s"add $retimeCtx")
            retimeCtx
          }.toList
          delayOps.foreach { op =>
            op.out.connected.foreach {
              case in@InputField(read:BufferRead, _) =>
                val ins = read.out.connected
                swapOutput(read.out, op.out)
                ins.foreach { in =>
                  bufferInput(in).foreach { bufferRead =>
                    transferLocalAccess(read, bufferRead)
                  }
                }
              case _ =>
            }
          }
          retimeCtxs
        }.toList
      }
    }

  def retimeOutput(out:Output[PIRNode], ins:Vector[Input[PIRNode]], externDelay:Int, fifoDepth:Int, numStage:Int):List[Delay] = {
    out.src match {
      case write:BufferWrite if !write.isFIFO => return Nil
      case write:TokenWrite => return Nil
      case _ =>
    }
    val depDelay = out.src match {
      case src:Delay => src.delay.get
      case src => src.ctx.get.delay.v.getOrElse(externDelay)
    }
    val toretime = ins.filter { in => 
      val deped = in.src.ctx.get
      val diff = deped.delay.get - depDelay + numStage
      diff > fifoDepth
    }
    if (toretime.isEmpty) return Nil
    val depedDelay = toretime.toStream.map { _.src.ctx.get.delay.get }.min
    // The earliest time to schedule this retime node is after its previous node and before any used
    // node
    val opdelay = math.min(depDelay + fifoDepth, depedDelay - 1)
    val d = within(out.src.getCtrl) { 
      stage(Delay(fifoDepth).in(out).delay(opdelay))
    }
    toretime.foreach { in =>
      swapConnection(in, out, d.out)
    }
    //breakPoint(s"add $d")
    d :: retimeOutput(d.out, toretime, externDelay, fifoDepth, numStage)
  }

}
