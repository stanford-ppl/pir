package pir
package pass

import pir.node._
import prism.graph._

trait BufferAnalyzer extends MemoryAnalyzer {
  def escape(dep:N, scope:N) = dep match {
    case dep:Memory => false 
    case dep:BufferWrite => false
    case dep:GlobalInput => false
    case dep:GlobalOutput => false
    case dep if dep.isDescendentOf(scope) => false
    case dep => true
  }

  def bufferInput(ctx:Context):Unit = dbgblk(s"bufferInput($ctx)"){
    ctx.descendents.foreach { deped => bufferInput(deped.as[PIRNode]) }
  }

  def bufferInput(deped:PIRNode):Seq[BufferRead] = {
    deped.localIns.flatMap { in => bufferInput(in) }
  }

  def bufferInput(in:Input):Seq[BufferRead] = {
    val deped = in.src.as[PIRNode]
    deped.localIns.toSeq.flatMap { _.connected }.distinct.flatMap { out =>
      bufferInput(out.as[Output], deped.as[PIRNode])
    }
  }

  def bufferOutput(out:Output):Seq[BufferRead] = {
    val dep = out.src.as[PIRNode]
    dep.localOuts.flatMap { out => 
      out.neighbors.flatMap { deped =>
        bufferInput(out, deped.as[PIRNode]) 
      }
    }
  }

  private def bufferInput(depOut:Output, deped:PIRNode):Option[BufferRead] = {
    val dep = depOut.src.as[PIRNode]
    val depedCtx = deped.ctx.get
    if (escape(dep, depedCtx)) {
      val read = dbgblk(s"bufferInput(depOut=$dep.${depOut}, deped=$deped)") {
        val depCtx = dep.ctx.get
        val isFIFO = dep.getCtrl == deped.getCtrl
        val (enq, deq) = compEnqDeq(dep.getCtrl, deped.getCtrl, isFIFO, depCtx, depedCtx)
        val write = within(depCtx, dep.getCtrl) {
          allocate[BufferWrite] { write => 
            write.data.traceInTo(depOut) &&
            write.done.traceInTo(enq)
          } {
            BufferWrite().data(depOut).done(enq)
          }
        }
        val read = within(depedCtx, deped.getCtrl) {
          allocate[BufferRead] { read => 
            read.in.traceInTo(write.out) &&
            read.done.traceInTo(deq)
          } {
            BufferRead(isFIFO).in(write.out).done(deq).banks(List(dep.getVec))
          }
        }
        swapInput(deped, depOut, read.out)
        read
      }
      Some(read)
    } else None
  }

  def insertGlobalInput(global:GlobalContainer):Unit = {
    within(global) {
      global.depsFrom.foreach { case (out, ins) => insertGlobalInput(global, out, ins) }
    }
  }

  def insertGlobalInput(
    global:GlobalContainer,
    out:Output, 
    ins:Seq[Input]
  ):GlobalInput = dbgblk(s"insertGlobalInput(${out.src}.${out}, $ins)"){
    out.src match {
      case dep:GlobalInput if dep.isDescendentOf(global) => dep
      case dep:GlobalInput => 
        ins.foreach { in => in.swapConnection(out, dep.in.T.out) }
        insertGlobalInput(global, dep.in.T.out, ins)
      case dep =>
        val gin = within(global) { 
          allocate[GlobalInput] { _.in.isConnectedTo(out) } { GlobalInput().in(out) } 
        }
        ins.foreach { _.swapConnection(out, gin.out) }
        gin
    }
  }

  def insertGlobalOutput(global:GlobalContainer):Unit = {
    within(global) {
      global.depedsTo.foreach { case (out, ins) => 
        insertGlobalOutput(global, out, ins)
      }
    }
  }

  def insertGlobalOutput(
    global:GlobalContainer,
    out:Output, 
    ins:Seq[Input]
  ):GlobalOutput = dbgblk(s"insertGlobalOutput(${out.src}.${out}, $ins)"){
    out.src match {
      case depedFrom:GlobalOutput if depedFrom.isDescendentOf(global) => depedFrom
      case depedFrom:GlobalOutput => throw PIRException(s"impossible case insertGlobalOutput")
      case depedFrom =>
        val gout = within(global) { 
          allocate[GlobalOutput]{ _.in.isConnectedTo(out) } { GlobalOutput().in(out) }
        }
        ins.foreach { _.swapConnection(out, gout.out) }
        gout
    }
  }

  def insertGlobalIO(global:GlobalContainer) = {
    insertGlobalInput(global)
    insertGlobalOutput(global)
  }


}

class BufferInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with BufferAnalyzer {
  val forward = false

  override def visitNode(n:N) = n match {
    case n:Context => bufferInput(n)
    case n => super.visitNode(n)
  }
}

