package pir.node

import pir._

import pirc._
import pirc.util._

trait ComputeNode extends PIRNode

case class CounterChain(counters:List[Counter])(implicit design:PIR) extends Container with ComputeNode
object CounterChain {
  def unit(implicit design:PIR) = {
    CounterChain(List(Counter(Const(0), Const(1), Const(1), 1)))
  }
}
case class Counter(min:Def, max:Def, step:Def, par:Int)(implicit design:PIR) extends Primitive with ComputeNode

case class ComputeContext()(implicit design:PIR) extends Container
