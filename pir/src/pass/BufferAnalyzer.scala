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
      case (_,depedIn) if depedCtx.streaming.get => true
      case (dep:LocalOutAccess, _) => false
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
      case dep:CounterReset => true
      case dep:Controller => true
      case dep => false
    }
  }

  case class BufferParam(
    fromCtx:Option[Context]=None, 
    isFIFO:Boolean=true,
    depCtrl:Option[ControlTree]=None,
    depedCtrl:Option[ControlTree]=None,
  )

  def bufferInput(ctx:Context)(implicit file:sourcecode.File, line: sourcecode.Line):Seq[BufferRead] = {
    bufferInput(ctx, BufferParam())
  }

  def bufferInput(ctx:Context, param:BufferParam)(implicit file:sourcecode.File, line: sourcecode.Line):Seq[BufferRead] = dbgblk(s"bufferInput($ctx)"){
    ctx.depsFrom.flatMap { case (out, ins) =>
      ins.flatMap { in =>
        insertBuffer(out, in, param)
      }
    }.toSeq
  }

  def bufferInput(in:Input[PIRNode], param:BufferParam=BufferParam())(implicit file:sourcecode.File, line: sourcecode.Line):Seq[BufferRead] = {
    in.connected.distinct.flatMap { out =>
      insertBuffer(out, in, param)
    }
  }

  def bufferInputFrom(out:Output[PIRNode], in:Input[PIRNode], fromCtx:Context)(implicit file:sourcecode.File, line: sourcecode.Line):Option[BufferRead] = {
    val saved = out.src.parent.get
    swapParent(out.src, fromCtx)
    val read = insertBuffer(out,in)
    swapParent(out.src, saved)
    read
  }

  def bufferOutput(out:Output[PIRNode])(implicit file:sourcecode.File, line: sourcecode.Line):Seq[BufferRead] = {
    out.connected.distinct.flatMap { in =>
      insertBuffer(out, in)
    }
  }

  def bufferOutputMulti(out:Output[PIRNode])(implicit file:sourcecode.File, line: sourcecode.Line) = {
    insertBuffers(out, out.connected.distinct)
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

  protected def insertBuffers(
    depOut:Output[PIRNode], 
    depedIns:List[Input[PIRNode]], 
    param:BufferParam = BufferParam(),
  )(implicit file:sourcecode.File, line: sourcecode.Line) = {
    val dep = depOut.src
    val anyEscape = depedIns.exists { deped =>
      escape(depOut, deped, deped.src.ctx.get)
    }
    if (anyEscape) {
      val depCtx = param.fromCtx.getOrElse { dep.ctx.get }
      val read = dbgblk(s"insertBuffer(depOut=${dquote(depOut)}, depedIns=$depedIns)") {
        val (enq, deq) = compEnqDeq(isFIFO=param.isFIFO, depCtx, depedIns.head.src.ctx.get, Some(depOut), depedIns)
        val bank = depOut.inferVec
        val depCtxCtrl = depCtx.getCtrl
        val depCtrl = param.depCtrl.map { depCtrl =>
          assert(depCtxCtrl.isDescendentOf(depCtrl) || depCtxCtrl == depCtrl, s"$depCtrl is not relate to $depCtxCtrl")
          depCtrl
        }.getOrElse(depCtxCtrl)
        val write = within(depCtx, depCtrl) {
          allocate[BufferWrite] { write => 
            write.isFIFO==param.isFIFO &&
            canReach(write.data,depOut) &&
            canReach(write.done,enq) &&
            !write.en.isConnected //TODO: buffer function should also allow enable as input
          } {
            stage(BufferWrite(isFIFO=param.isFIFO).data(depOut).done(enq))
          }
        }
        depedIns.foreach { depedIn =>
          val depedCtx = depedIn.src.ctx.get
          val globalbb = depedIn.src.isInstanceOf[GlobalBlackBox]
          val (enq, deq) = compEnqDeq(isFIFO=param.isFIFO, depCtx, depedCtx, Some(depOut), depedIns)
          val depedCtxCtrl = depedCtx.getCtrl
          val depedCtrl = param.depedCtrl.map { depedCtrl =>
            assert(depedCtxCtrl.isDescendentOf(depedCtrl) || depedCtxCtrl == depedCtrl, s"$depedCtrl is not relate to $depedCtxCtrl")
            depedCtrl
          }.getOrElse(depedCtxCtrl)
          val read = within(depedCtx, depedCtrl) {
            allocate[BufferRead] { read => 
              !globalbb && 
              read.isFIFO==param.isFIFO &&
              canReach(read.in,write.out) &&
              canReach(read.done,deq) &&
              !read.en.isConnected 
            } {
              stage(BufferRead(isFIFO=param.isFIFO).in(write.out).done(deq))
            }
          }
          bank.foreach { bank => read.banks(List(bank)) }
          swapConnection(depedIn, depOut, read.out)
        }
      }
    } 
  }

  protected def insertBuffer(
    depOut:Output[PIRNode], 
    depedIn:Input[PIRNode], 
    param:BufferParam = BufferParam(),
  )(implicit file:sourcecode.File, line: sourcecode.Line):Option[BufferRead] = {
    val dep = depOut.src
    val deped = depedIn.src
    val depedCtx = deped.ctx.get
    if (escape(depOut, depedIn, depedCtx)) {
      val depCtx = param.fromCtx.getOrElse { dep.ctx.get }
      val read = dbgblk(s"insertBuffer(depOut=${dquote(depOut)}, depedIn=$deped.$depedIn)") {
        val (enq, deq) = compEnqDeq(isFIFO=param.isFIFO, depCtx, depedCtx, Some(depOut), List(depedIn))
        val bank = depOut.inferVec
        val depCtxCtrl = depCtx.getCtrl
        val depCtrl = param.depCtrl.map { depCtrl =>
          assert(depCtxCtrl.isDescendentOf(depCtrl) || depCtxCtrl == depCtrl, s"$depCtrl is not relate to $depCtxCtrl")
          depCtrl
        }.getOrElse(depCtxCtrl)
        val write = within(depCtx, depCtrl) {
          allocate[BufferWrite] { write => 
            write.isFIFO==param.isFIFO &&
            canReach(write.data,depOut) &&
            canReach(write.done,enq) &&
            !write.en.isConnected //TODO: buffer function should also allow enable as input
          } {
            stage(BufferWrite(isFIFO=param.isFIFO).data(depOut).done(enq))
          }
        }
        val globalbb = depedIn.src.isInstanceOf[GlobalBlackBox]
        val depedCtxCtrl = depedCtx.getCtrl
        val depedCtrl = param.depedCtrl.map { depedCtrl =>
          assert(depedCtxCtrl.isDescendentOf(depedCtrl) || depedCtxCtrl == depedCtrl, s"$depedCtrl is not relate to $depedCtxCtrl")
          depedCtrl
        }.getOrElse(depedCtxCtrl)
        val read = within(depedCtx, depedCtrl) {
          allocate[BufferRead] { read => 
            !globalbb && 
            read.isFIFO==param.isFIFO &&
            canReach(read.in,write.out) &&
            canReach(read.done,deq) &&
            !read.en.isConnected 
          } {
            stage(BufferRead(isFIFO=param.isFIFO).in(write.out).done(deq))
          }
        }
        bank.foreach { bank => read.banks(List(bank)) }
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

