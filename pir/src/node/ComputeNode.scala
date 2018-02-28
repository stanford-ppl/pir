package pir.node

import pir._

trait ComputeNode extends PIRNode

case class CounterChain(counters:List[Counter])(implicit design:Design) extends Container with ComputeNode
object CounterChain {
  def unit(implicit design:Design) = {
    CounterChain(List(Counter(Const(0), Const(1), Const(1), 1)))
  }
}
case class Counter(min:Def, max:Def, step:Def, par:Int)(implicit design:Design) extends Primitive with ComputeNode
case class EnabledCounter(min:Def, max:Def, step:Def, par:Int, en:Def)(implicit design:Design) extends Primitive with ComputeNode
case class CounterDone(counter:Primitive)(implicit design:Design) extends ControlNode

case class ComputeContext()(implicit design:Design) extends Container

// Place holder for ContextEnable. Lowered to ContextEnable
case class ContextEnableOut()(implicit design:Design) extends ControlNode
case class ContextEnable(enables:List[Def])(implicit design:Design) extends ControlNode
