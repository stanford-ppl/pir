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

  def bufferInput(deped:PIRNode):Unit = {
    val depedCtx = deped.ctx.get
    deped.localDeps.foreach { dep =>
      if (escape(dep, depedCtx)) bufferInput(dep.as[PIRNode], deped)
    }
  }

  def bufferInput(in:Input):Unit = {
    val deped = in.src.as[PIRNode]
    assertOneOrLess(in.neighbors, s"$deped.$in.neighbors").foreach { dep =>
      bufferInput(dep.as[PIRNode], deped)
    }
  }

  def bufferOutput(out:Output):Unit = {
    val dep = out.src.as[PIRNode]
    val depeds = out.neighbors
    depeds.foreach { deped =>
      bufferInput(dep, deped.as[PIRNode])
    }
  }

  private def bufferInput(dep:PIRNode, deped:PIRNode):Unit = dbgblk(s"bufferInput(dep=$dep, deped=$deped)"){
    val depCtx = dep.ctx.get
    val depedCtx = deped.ctx.get
    val (enq, deq) = compEnqDeq(dep.ctrl.get, deped.ctrl.get, false, depCtx, depedCtx)
    val write = assertOneOrLess(depCtx.collectDown[BufferWrite]().filter { write =>
      write.data.traceTo(dep) &&
      write.done.traceTo(enq)
    }, s"bufferWrite from $dep with done=$enq in $depCtx").getOrElse {
      within(depCtx, dep.ctrl.get) {
        val write = BufferWrite().data(dep).done(enq)
        dbg(s"create $write.data($dep).done($enq)")
        //bufferInput(write)
        write
      }
    }
    val read = assertOneOrLess(depedCtx.collectDown[BufferRead]().filter { read =>
      read.in.traceTo(write) &&
      read.done.traceTo(deq)
    }, s"bufferRead from $write with done=$deq in $depedCtx").getOrElse {
      within(depedCtx, deped.ctrl.get) {
        val read = BufferRead(isFIFO=dep.ctrl.get == deped.ctrl.get).in(write).done(deq)
        dbg(s"create $read.in($write).done($deq)")
        //bufferInput(read)
        read
      }
    }
    swapConnection(deped, dep.as[PIRNode].output.get, read.out)
  }
}

class BufferInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with BufferAnalyzer {
  val forward = false

  override def visitNode(n:N) = n match {
    case n:Context => bufferInput(n)
    case n => super.visitNode(n)
  }
}

