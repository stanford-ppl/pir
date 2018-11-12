package pir
package pass

import pir.node._
import prism.graph._

class RouteThroughElimination(implicit compiler:PIR) extends PIRTraversal with Transformer with SiblingFirstTraversal with UnitTraversal {

  var validPropHasRun = false
  var memLowerHasRun = false

  override def initPass = {
    super.initPass
    memLowerHasRun = false
    memLowerHasRun = compiler.hasRun[MemoryLowering]
    validPropHasRun = false
    validPropHasRun = compiler.hasRun[ValidConstantPropogation]
  }

  override def visitNode(n:N) = n match {
    // Rule 1
    case n:MemWrite if validPropHasRun && !memLowerHasRun && n.en.T.isEmpty =>
      n.data.T match {
        case data:MemRead if data.en.T.isEmpty =>
          val dataWriters = data.mem.T.inAccesses
          if (dataWriters.size == 1) {
            val dataWriter = dataWriters.head.as[MemWrite]
            val nMem = n.mem.T
            dbg(s"Route through $dataWriter -> $data -> $n -> $nMem detected")
            disconnect(n.mem, nMem)
            val mdataWriter = within(dataWriter.parent.get.as[PIRNode]) {
              mirrorAll(List(dataWriter))(dataWriter).as[MemWrite]
            }
            mdataWriter.mem(nMem)
          }
        case _ =>
      }
    // Rule 2
    case n:BufferWrite if memLowerHasRun => 
      n.data.T match {
        case data:BufferRead if data.done.evalTo(n.done.T) =>
          val dataWrite:LocalInAccess = data.in.T
          val nOut = n.out.T
          disconnect(dataWrite.out, data)
          nOut.foreach { nOut => disconnect(n.out, nOut) }
          dataWrite.out(nOut.map { _.in })
          dbg(s"Route through $dataWrite -> $data -> $n -> $nOut detected")
        case _ => 
      }
    case n => super.visitNode(n)
  }
}
