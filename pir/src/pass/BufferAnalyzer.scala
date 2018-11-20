package pir
package pass

import pir.node._
import prism.graph._

trait BufferAnalyzer extends MemoryAnalyzer {
  def escape(dep:N, scope:N) = dep match {
    case dep:Memory => false 
    case dep:BufferWrite => false
    case dep:GlobalInput => false
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
    deped.localIns.flatMap { in =>
      in.connected.flatMap { out =>
        bufferInput(out.as[Output], deped.as[PIRNode])
      }
    }
  }

  def bufferOutput(out:Output):Seq[BufferRead] = {
    val dep = out.src.as[PIRNode]
    dep.localOuts.flatMap { out => 
      out.connected.flatMap { in => 
        bufferInput(out, in.src.as[PIRNode]) 
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
            BufferWrite().data(dep).done(enq)
          }
        }
        val read = within(depedCtx, deped.getCtrl) {
          allocate[BufferRead] { read => 
            read.in.traceInTo(write.out) &&
            read.done.traceInTo(deq)
          } {
            BufferRead(isFIFO).in(write).done(deq).banks(List(dep.getVec))
          }
        }
        swapInput(deped, depOut, read.out)
        read
      }
      Some(read)
    } else None
  }

  def insertGlobalInput(
    global:GlobalContainer,
    dep:N, 
    depFroms:Seq[N]
  ):GlobalInput = dbgblk(s"insertGlobalInput($dep, $depFroms)"){
    dep match {
      case dep:GlobalInput if dep.isDescendentOf(global) => dep
      case dep:GlobalInput => 
        depFroms.foreach { deped =>
          swapInput(deped, dep.out, dep.in.T.out)
        }
        insertGlobalInput(global, dep.in.T, depFroms)
      case dep =>
        val gin = GlobalInput()
        depFroms.foreach { depFrom =>
          insertConnection(dep, depFrom, gin.in, gin.out)
        }
        gin
    }
  }

  def insertGlobalOutput(
    global:GlobalContainer,
    depedFrom:N, 
    depeds:Seq[N]
  ):GlobalOutput = dbgblk(s"insertGlobalOutput($depedFrom, $depeds)"){
    depedFrom match {
      case depedFrom:GlobalOutput if depedFrom.isDescendentOf(global) => depedFrom
      case depedFrom:GlobalOutput => throw PIRException(s"impossible case insertGlobalOutput")
      case depedFrom =>
        val gout = GlobalOutput()
        depeds.foreach { deped =>
          insertConnection(depedFrom, deped, gout.in, gout.out)
        }
        gout
    }
  }

  def insertGlobalIO(global:GlobalContainer) = {
    within(global) {
      global.depsFrom.foreach { case (dep, depFroms) => insertGlobalInput(global, dep, depFroms) }
      global.depedsTo.foreach { case (depedFrom, depeds) => insertGlobalOutput(global, depedFrom, depeds) }
    }
  }


}

class BufferInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with BufferAnalyzer {
  val forward = false

  override def visitNode(n:N) = n match {
    case n:Context => bufferInput(n)
    case n => super.visitNode(n)
  }
}

