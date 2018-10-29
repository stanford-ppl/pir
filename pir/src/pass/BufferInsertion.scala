package pir
package pass

import pir.node._
import prism.graph._

class BufferInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with MemoryAnalyzer {
  val forward = false

  override def visitNode(n:N) = n match {
    case n:Context => bufferInput(n)
    case n => super.visitNode(n)
  }

}

trait MemoryAnalyzer extends PIRPass with Transformer {

  def bufferInput(ctx:Context):Unit = dbgblk(s"bufferInput($ctx)"){
    val descendents = ctx.descendents
    val depDepeds = descendents.flatMap[(N, Seq[N]), Seq[(N, Seq[N])]] { deped =>
      if (!deped.isInstanceOf[MemoryNode]) {
        val deps = deped.localDeps.filter { dep => !dep.isInstanceOf[MemoryNode] && !descendents.contains(dep) }
        if (deps.nonEmpty) Some((deped, deps)) else None
      } else None
    }
    depDepeds.foreach { case (deped, deps) =>
      dbg(s"deped=$deped, deps=$deps")
      deps.foreach { dep =>
        bufferInput(dep.to[PIRNode], deped.to[PIRNode])
      }
    }
    //breakPoint(s"bufferInput($ctx)")
  }

  def bufferInput(deped:PIRNode):Unit = {
    if (deped.isInstanceOf[MemoryNode]) return
    val depedCtx = deped.collectUp[Context]().head
    deped.localDeps.foreach { dep =>
      if (!dep.isInstanceOf[MemoryNode] && !dep.isDescendentOf(depedCtx) ) bufferInput(dep.to[PIRNode], deped)
    }
  }

  private def bufferInput(dep:PIRNode, deped:PIRNode):Unit = dbgblk(s"bufferInput(dep=$dep, deped=$deped)"){
    val depCtx = dep.collectUp[Context]().head
    val depedCtx = deped.collectUp[Context]().head
    val (enq, deq) = compEnqDeq(dep.ctrl.get, deped.ctrl.get, false)
    val write = assertOneOrLess(depCtx.collectDown[BufferWrite]().filter { write =>
      write.data.traceTo(dep) &&
      write.en.traceTo(enq)
    }, s"bufferWrite from $dep with en=$enq in $depCtx").getOrElse {
      within(depCtx, dep.ctrl.get) {
        val write = BufferWrite().data(dep).en(enq)
        dbg(s"create $write")
        bufferInput(write)
        write
      }
    }
    val read = assertOneOrLess(depedCtx.collectDown[BufferRead]().filter { read =>
      read.in.traceTo(write) &&
      read.en.traceTo(deq)
    }, s"bufferRead from $write with en=$deq in $depedCtx").getOrElse {
      within(depedCtx, deped.ctrl.get) {
        val read = BufferRead(isFIFO=dep.ctrl.get == deped.ctrl.get).in(write).en(deq)
        dbg(s"create $read")
        bufferInput(read)
        read
      }
    }
    swapConnection(deped, dep.to[PIRNode].output.get, read.out)
  }

  def compEnqDeq(a:ControlTree, b:ControlTree, isFIFO:Boolean):(PIRNode, PIRNode) = 
  dbgblk(s"compEnqDeq($a, $b, isFIFO=$isFIFO)"){
    if (a == b || isFIFO) {
      (ctrlValid(a), ctrlValid(b))
    } else if (a.isAncestorOf(b)) {
      val bAncesstors = (b::b.ancestors)
      val idx = bAncesstors.indexOf(a)
      val ctrl = bAncesstors(idx-1).to[ControlTree]
      (ctrlValid(a), ctrlDone(ctrl))
    } else if (b.isAncestorOf(a)) {
      compEnqDeq(b,a,isFIFO) 
    } else {
      val lca = leastCommonAncesstor(a,b).get
      val aAncesstors = (a::a.ancestors)
      val bAncesstors = (b::b.ancestors)
      val aidx = aAncesstors.indexOf(lca)
      val bidx = bAncesstors.indexOf(lca)
      val actrl = aAncesstors(aidx-1).to[ControlTree]
      val bctrl = bAncesstors(bidx-1).to[ControlTree]
      (ctrlDone(actrl), ctrlDone(bctrl))
    }
  }

  def ctrlValid(ctrl:ControlTree):PIRNode = {
    assertOne(ctrl.pnodes.get.collect { case ctrl:Controller => ctrl }, 
      s"controller for $ctrl").valid
  }

  def ctrlDone(ctrl:ControlTree):PIRNode = ctrl match {
    case ctrl if ctrl == pirTop.hostInCtrl => pirTop.hostInDone
    case ctrl if ctrl == pirTop.hostOutCtrl => pirTop.hostOutDone
    case ctrl => 
      assertOne(ctrl.pnodes.get.collect { case ctrl:Controller => ctrl }, 
        s"controller for $ctrl").done
  }

}
