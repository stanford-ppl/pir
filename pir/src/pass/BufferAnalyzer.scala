package pir
package pass

import pir.node._
import prism.graph._

trait BufferAnalyzer extends MemoryAnalyzer { self:PIRTransformer =>
  /*
   * escaped node will be buffered between dep ctx and scope
   * */
  def escape(depOut:Output[PIRNode], depedIn:Input[PIRNode], depedCtx:Context):Boolean = {
    val dep = depOut.src
    if (dep.isDescendentOf(depedCtx)) return false
    if (!dep.isUnder[Context]) return false
    (dep, depedIn) match {
      case (_,InputField(deped:LocalOutAccess, "in")) => false
      case (dep:LocalOutAccess, _) => false
      case (_,depedIn) if depedCtx.streaming.get => true
      case (dep,_) => !canDuplicate(depOut)
    }
  }

  def canDuplicate(depOut:Output[PIRNode]) = {
    val dep = depOut.src
    dep match {
      case dep:Const => true
      case dep:LocalOutAccess => true
      case dep:CounterIter => true
      case dep:CounterValid => true
      case dep:Controller => true
      case dep => false
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

  def bufferInput(in:Input[PIRNode], fromCtx:Option[Context]=None, isFIFO:Boolean=true):Seq[BufferRead] = {
    in.connected.distinct.flatMap { out =>
      insertBuffer(out, in, fromCtx, isFIFO)
    }
  }

  def bufferInputFrom(out:Output[PIRNode], in:Input[PIRNode], fromCtx:Context):Option[BufferRead] = {
    val saved = out.src.parent.get
    swapParent(out.src, fromCtx)
    val read = insertBuffer(out,in)
    swapParent(out.src, saved)
    read
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

  def canReach(in:Input[PIRNode], out:Output[PIRNode]) = {
    in.canReach(out, visitEdges=visitInEdges _)
  }

  private def insertBuffer(depOut:Output[PIRNode], depedIn:Input[PIRNode], fromCtx:Option[Context]=None, isFIFO:Boolean=true):Option[BufferRead] = {
    val dep = depOut.src
    val deped = depedIn.src
    val depedCtx = deped.ctx.get
    if (escape(depOut, depedIn, depedCtx)) {
      val depCtx = fromCtx.getOrElse { dep.ctx.get }
      val read = dbgblk(s"insertBuffer(depOut=${dquote(depOut)}, depedIn=$deped.$depedIn)") {
        val (enq, deq) = compEnqDeq(isFIFO=isFIFO, depCtx, depedCtx, Some(depOut), List(depedIn))
        val write = within(depCtx, depCtx.getCtrl) {
          allocate[BufferWrite] { write => 
            write.isFIFO==isFIFO &&
            canReach(write.data,depOut) &&
            canReach(write.done,enq) &&
            !write.en.isConnected //TODO: buffer function should also allow enable as input
          } {
            stage(BufferWrite(isFIFO=isFIFO).data(depOut).done(enq))
          }
        }
        val read = within(depedCtx, depedCtx.getCtrl) {
          allocate[BufferRead] { read => 
            read.isFIFO==isFIFO &&
            canReach(read.in,write.out) &&
            canReach(read.done,deq) &&
            !read.en.isConnected
          } {
            stage(BufferRead(isFIFO=isFIFO).in(write.out).done(deq))
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

