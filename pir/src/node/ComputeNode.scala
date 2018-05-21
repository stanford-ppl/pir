package pir
package node

trait ComputeNode extends PIRNode

/*
 * @param counters: loop iterators. from outer most to inner most
 * */
case class CounterChain(counters:List[Counter])(implicit design:PIRDesign) extends Container with ComputeNode {
  def outer = counters.head
  def inner = counters.last
  if (design.staging) {
    counters.sliding(2).foreach { 
      case List(octr, ictr) => octr.setEnable(ictr)
      case _ =>
    }
  }
}
case class CounterChainEnable(en:Def)(implicit design:PIRDesign) extends Primitive with ComputeNode
object CounterChain {
  def unit(implicit design:PIRDesign) = {
    CounterChain(List(Counter(Const(0), Const(1), Const(1), 1)))
  }
}
case class Counter(min:Def, max:Def, step:Def, par:Int)(implicit design:PIRDesign) extends Primitive with ComputeNode {
  def getEnable = {
    val Def(_, Counter(min, max, step, par)) = this
    val ens = visitGlobalIn[PIRNode](this).filterNot { in => List(min, max, step).contains(in) }
    assert(ens.size<=1, s"$this has more than 1 enable $ens")
    ens.headOption.asInstanceOf[Option[Primitive]]
  }
  def setEnable(en:Primitive) = {
    getEnable.fold[Unit]{
      this.connect(en.out)
    } { enable => assert(enable == en, s"reset enable of $this to $en, enable=$enable") }
  }
}
case class CounterDone(counter:Primitive)(implicit design:PIRDesign) extends ControlNode

case class ComputeContext()(implicit design:PIRDesign) extends Container

// Place holder for ContextEnable. Lowered to ContextEnable
case class ContextEnableOut()(implicit design:PIRDesign) extends ControlNode
case class ContextEnable(enables:List[Def])(implicit design:PIRDesign) extends ControlNode

trait PIRComputeNode {
  def isCounter(n:PIRNode) = n match {
    case n:Counter => true
    case _ => false
  }

  def ctxEnOf(n:Container):Option[ContextEnable] = {
    n.collectDown[ContextEnable]().headOption
  }

  def ctxEnOf(n:Primitive):Option[ContextEnable] = {
    if (within[ComputeContext](n)) {
      Some(n.collectPeer[ContextEnable]().headOption.getOrElse {
        n.collectInTillMem[ContextEnable]().head
      })
    } else None
  }

  def loadAccessesOf(n:ContextEnable) = {
    n.collectOutTillMem[LocalLoad]() //reads enabled by this contextEnable
  }

  def inMemsOf(n:ContextEnable) = {
    var reads = loadAccessesOf(n)
    reads = reads.filter { read => memsOf(read).forall { mem => writersOf(mem).nonEmpty } }
    reads.flatMap { read => memsOf(read) }
  }

  def storeAccessesOf(n:ContextEnable) = {
    n.collectOutTillMem[LocalStore]() // writes enabled by this contextEnable
  }

  def outMemsOf(n:ContextEnable) = {
    val writes = storeAccessesOf(n)
    writes.flatMap { write => memsOf(write) }
  }

}
