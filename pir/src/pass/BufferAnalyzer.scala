package pir
package pass

import pir.node._
import prism.graph._

trait BufferAnalyzer extends MemoryAnalyzer {
  /*
   * escaped node will be buffered between dep ctx and scope
   * */
  def escape(dep:PIRNode, depedIn:Input[PIRNode], depedCtx:Context) = {
    (dep, depedIn, depedCtx) match {
      case (_, InputField(deped:Access, "mem"), _) => false
      case (_, InputField(deped:LocalOutAccess, "in"), _) => false
      case (dep, _, _) if dep.isDescendentOf(depedCtx) => false

      case (_,_,DRAMContext(cmd)) => true

      case (dep:Const, _, _) => false // duplicate later
      case (dep:CounterIter, _, _) => false // duplicate later
      case (dep:CounterValid, _, _) => false // duplicate later
      case (dep:Controller, _, _) => false // duplicate later
      case (dep:LocalOutAccess, _, _) => false // duplicate later

      case (dep, _, _) => true
    }
  }

  def bufferInput(ctx:Context):Unit = dbgblk(s"bufferInput($ctx)"){
    ctx.descendents.foreach { deped => bufferInput(deped) }
  }

  def bufferInput(deped:PIRNode):Seq[BufferRead] = {
    deped.localIns.flatMap { in => bufferInput(in) }
  }

  def bufferInput(in:Input[PIRNode]):Seq[BufferRead] = {
    in.connected.distinct.flatMap { out =>
      bufferInput(out, in)
    }
  }

  def bufferOutput(out:Output[PIRNode]):Seq[BufferRead] = {
    out.connected.distinct.flatMap { in =>
      bufferInput(out, in)
    }
  }

  private def visitInEdges(n:Node[PIRNode]):List[Input[PIRNode]] = n match {
    case n:BufferRead => List(n.in)
    case n:BufferWrite => List(n.data)
    case n:GlobalInput => List(n.in)
    case n:GlobalOutput => List(n.in)
    case n => Nil
  }

  private def bufferInput(depOut:Output[PIRNode], depedIn:Input[PIRNode]):Option[BufferRead] = {
    val dep = depOut.src
    val deped = depedIn.src
    val depedCtx = deped.ctx.get
    if (escape(dep, depedIn, depedCtx)) {
      val read = dbgblk(s"bufferInput(depOut=$dep.$depOut, depedIn=$deped.$depedIn)") {
        val depCtx = dep.ctx.get
        val (enq, deq) = compEnqDeq(isFIFO=true, depCtx, depedCtx, Some(depOut), List(depedIn))
        val write = within(depCtx, dep.getCtrl) {
          allocate[BufferWrite] { write => 
            write.data.canReach(depOut, visitEdges=visitInEdges _) &&
            write.done.canReach(enq, visitEdges=visitInEdges _)
          } {
            BufferWrite().data(depOut).done(enq)
          }
        }
        val read = within(depedCtx, deped.getCtrl) {
          allocate[BufferRead] { read => 
            read.in.canReach(write.out, visitEdges=visitInEdges _) &&
            read.done.canReach(deq, visitEdges=visitInEdges _)
          } {
            BufferRead().in(write.out).done(deq).banks(List(dep.getVec))
          }
        }
        swapConnection(depedIn, depOut, read.out)
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
    out:Output[PIRNode], 
    inputs:Seq[Input[PIRNode]]
  ):Unit = {
    val ins = inputs.filterNot { _.src.isInstanceOf[GlobalInput] }
    if (ins.isEmpty) return
    dbgblk(s"insertGlobalInput($global, ${out.src}.${out}, $ins)"){
      out.src match {
        case dep:GlobalInput if dep.isDescendentOf(global) => dep
        case dep:GlobalInput => 
          ins.foreach { in => swapConnection(in, out, dep.in.T.out) }
          insertGlobalInput(global, dep.in.T.out, ins)
        case dep =>
          val gin = within(global) { 
            allocate[GlobalInput] { _.in.isConnectedTo(out) } { GlobalInput().in(out) } 
          }
          ins.foreach { in => swapConnection(in, out, gin.out) }
          gin
      }
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
    out:Output[PIRNode], 
    ins:Seq[Input[PIRNode]]
  ):GlobalOutput = dbgblk(s"insertGlobalOutput($global, ${out.src}.${out}, $ins)"){
    out.src match {
      case depedFrom:GlobalOutput if depedFrom.isDescendentOf(global) => depedFrom
      case depedFrom:GlobalOutput => throw PIRException(s"impossible case insertGlobalOutput")
      case depedFrom =>
        val gout = within(global) { 
          allocate[GlobalOutput]{ _.in.isConnectedTo(out) } { GlobalOutput().in(out) }
        }
        ins.foreach { in => swapConnection(in, out, gout.out) }
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

