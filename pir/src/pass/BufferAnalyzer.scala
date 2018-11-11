package pir
package pass

import pir.node._
import prism.graph._

trait BufferAnalyzer extends MemoryAnalyzer {
  def escape(dep:N, scope:N) = dep match {
    case dep:Memory => false 
    case dep:BufferWrite => false
    case dep if dep.isDescendentOf(scope) => false
    case dep => true
  }

  def bufferInput(ctx:Context):Unit = dbgblk(s"bufferInput($ctx)"){
    ctx.descendents.foreach { deped =>
      deped.localDeps.foreach { dep => 
        if (escape(dep, ctx)) bufferInput(dep.as[PIRNode], deped.as[PIRNode])
      }
    }
  }

  def bufferInput(deped:PIRNode):Seq[BufferRead] = {
    val depedCtx = deped.ctx.get
    deped.localDeps.flatMap { dep =>
      if (escape(dep, depedCtx)) Some(bufferInput(dep.as[PIRNode], deped))
      else None
    }
  }

  def bufferInput(in:Input):Seq[BufferRead] = {
    val deped = in.src.as[PIRNode]
    in.neighbors.map { dep =>
      bufferInput(dep.as[PIRNode], deped)
    }
  }

  def bufferOutput(out:Output):Seq[BufferRead] = {
    val dep = out.src.as[PIRNode]
    val depeds = out.neighbors
    depeds.map { deped =>
      bufferInput(dep, deped.as[PIRNode])
    }
  }

  private def bufferInput(dep:PIRNode, deped:PIRNode):BufferRead = dbgblk(s"bufferInput(dep=$dep, deped=$deped)"){
    val depCtx = dep.ctx.get
    val depedCtx = deped.ctx.get
    val isFIFO = dep.getCtrl == deped.getCtrl
    val (enq, deq) = compEnqDeq(dep.getCtrl, deped.getCtrl, isFIFO, depCtx, depedCtx)
    val write = within(depCtx, dep.getCtrl) {
      allocate[BufferWrite] { write => 
        write.data.traceTo(dep) &&
        write.done.traceTo(enq)
      } {
        BufferWrite().data(dep).done(enq)
      }
    }
    val read = within(depedCtx, deped.getCtrl) {
      allocate[BufferRead] { read => 
        read.in.traceTo(write) &&
        read.done.traceTo(deq)
      } {
        BufferRead(isFIFO).in(write).done(deq).banks(List(dep.getVec))
      }
    }
    swapConnection(deped, dep.as[PIRNode].output.get, read.out)
    read
  }

}

class BufferInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with BufferAnalyzer {
  val forward = false

  override def visitNode(n:N) = n match {
    case n:Context => bufferInput(n)
    case n => super.visitNode(n)
  }
}

