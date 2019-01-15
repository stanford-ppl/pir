package pir
package pass

import pir.node._
import prism.graph._

class RouteThroughElimination(implicit compiler:PIR) extends PIRTraversal with Transformer with SiblingFirstTraversal with UnitTraversal {

  var validPropHasRun = false
  var memLowerHasRun = false
  var globalInsertHasRun = false

  override def initPass = {
    super.initPass
    memLowerHasRun = compiler.hasRun[MemoryLowering]
    validPropHasRun = compiler.hasRun[ValidConstantPropogation]
    globalInsertHasRun = compiler.hasRun[GlobalInsertion]
  }

  override def visitNode(n:N) = n match {
    // Rule 1
    case n:MemWrite if validPropHasRun && !memLowerHasRun && !globalInsertHasRun && !n.en.isConnected =>
      n.data.T match {
        case read:MemRead if !read.en.isConnected =>
          val dataWriters = read.mem.T.inAccesses
          if (dataWriters.size == 1) {
            val dataWriter = dataWriters.head.as[MemWrite]
            val nMem = n.mem.T
            disconnect(n.mem, nMem)
            val mdataWriter = within(dataWriter.parent.get.as[PIRNode]) {
              mirrorAll(List(dataWriter))(dataWriter).as[MemWrite]
            }
            mdataWriter.mem(nMem)
            dbg(s"Route through $dataWriter -> $read -> $n -> $nMem detected")
            dbg(s" => $mdataWriter -> $nMem")
          }
        case _ =>
      }
    // Rule 2
    case n:BufferWrite if memLowerHasRun && !globalInsertHasRun => 
      n.data.T match {
        case read:BufferRead if read.done.evalTo(n.done.T) =>
          val dataWrite = read.inAccess
          val nOut = n.outAccesses
          disconnect(dataWrite.out, read)
          nOut.foreach { nOut => disconnect(n.out, nOut) }
          dataWrite.out(nOut.map { _.in })
          dbg(s"Route through $dataWrite -> $read -> $n -> $nOut detected")
          dbg(s" => $dataWrite -> $nOut")
        case _ => 
      }
    case n => super.visitNode(n)
  }
}
