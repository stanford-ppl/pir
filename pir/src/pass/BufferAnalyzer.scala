package pir
package pass

import pir.node._
import prism.graph._

trait BufferAnalyzer extends MemoryAnalyzer { self:PIRTransformer =>
  /*
   * escaped node will be buffered between dep ctx and scope
   * */
  def escape(dep:PIRNode, depedIn:Input[PIRNode], depedCtx:Context) = {
    (dep, depedIn, depedCtx) match {
      case (dep, _, _) if dep.isDescendentOf(depedCtx) => false
      case (dep, _, _) if !dep.isUnder[Context] => false
      case (_, InputField(deped:LocalOutAccess, "in"), _) => false

      case (_,_,ctx) if ctx.streaming.get => true

      case (dep:Const, _, _) => false // duplicate later
      case (dep:CounterIter, _, _) => false // duplicate later
      case (dep:CounterValid, _, _) => false // duplicate later
      case (dep:Controller, _, _) => false // duplicate later
      case (dep:LocalOutAccess, _, _) => false // duplicate later

      case (dep, _, _) => true
    }
  }

  def bufferInput(ctx:Context):Unit = {
    bufferInput(ctx, None)
  }

  def bufferInput(ctx:Context, fromCtx:Option[Context]):Unit = dbgblk(s"bufferInput($ctx)"){
    ctx.descendents.foreach { deped => bufferInput(deped, fromCtx) }
  }

  def bufferInput(deped:PIRNode, fromCtx:Option[Context]):Seq[BufferRead] = {
    deped.localIns.flatMap { in => bufferInput(in, fromCtx) }
  }

  def bufferInput(in:Input[PIRNode], fromCtx:Option[Context]=None):Seq[BufferRead] = {
    in.connected.distinct.flatMap { out =>
      insertBuffer(out, in, fromCtx)
    }
  }

  def bufferOutput(out:Output[PIRNode]):Seq[BufferRead] = {
    out.connected.distinct.flatMap { in =>
      insertBuffer(out, in)
    }
  }

  private def visitInEdges(n:Node[PIRNode]):List[Input[PIRNode]] = n match {
    case n:BufferRead => List(n.in)
    case n:BufferWrite => List(n.data)
    case n:GlobalInput => List(n.in)
    case n:GlobalOutput => List(n.in)
    case n => Nil
  }

  private def insertBuffer(depOut:Output[PIRNode], depedIn:Input[PIRNode], fromCtx:Option[Context]=None):Option[BufferRead] = {
    val dep = depOut.src
    val deped = depedIn.src
    val depedCtx = deped.ctx.get
    if (escape(dep, depedIn, depedCtx)) {
      val depCtx = fromCtx.getOrElse { dep.ctx.get }
      val read = dbgblk(s"insertBuffer(depOut=$dep.$depOut, depedIn=$deped.$depedIn)") {
        val (enq, deq) = compEnqDeq(isFIFO=true, depCtx, depedCtx, Some(depOut), List(depedIn))
        val write = within(depCtx, depCtx.getCtrl) {
          allocate[BufferWrite] { write => 
            write.isFIFO &&
            write.data.canReach(depOut, visitEdges=visitInEdges _) &&
            write.done.canReach(enq, visitEdges=visitInEdges _) &&
            !write.en.isConnected //TODO: buffer function should also allow enable as input
          } {
            stage(BufferWrite(isFIFO=true).data(depOut).done(enq))
          }
        }
        val read = within(depedCtx, depedCtx.getCtrl) {
          allocate[BufferRead] { read => 
            read.isFIFO &&
            read.in.canReach(write.out, visitEdges=visitInEdges _) &&
            read.done.canReach(deq, visitEdges=visitInEdges _) &&
            !read.en.isConnected
          } {
            stage(BufferRead(isFIFO=true).in(write.out).done(deq))
          }
        }
        swapConnection(depedIn, depOut, read.out)
        read
      }
      Some(read)
    } else None
  }

}

class BufferInsertion(implicit compiler:PIR) extends PIRTraversal with PIRTransformer with SiblingFirstTraversal with UnitTraversal {
  val forward = false

  override def visitNode(n:N) = n match {
    case n:Context => bufferInput(n)
    case n => super.visitNode(n)
  }
}

