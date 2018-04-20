package pir.node

abstract class Def(implicit design:PIRDesign) extends Primitive with ComputeNode { self =>
  def depDefs:Set[Def] = deps.collect { case d:Def => d } 
  def localDepDefs = localDeps.collect { case d:Def => d } 
  def depedDefs:Set[Def] = depeds.collect { case d:Def => d } 
  def localDepedDefs = localDepeds.collect { case d:Def => d } 
}
object Def {
  def unapply[T:ClassTag](x:T):Option[(T, T)] = {
    x match {
      case x:T => 
        val n = x.asInstanceOf[PIRNode]
        Some((x, n.newInstance(n.values, staging=false)))
      case _ => None
    }
  }
}

trait StageDef extends Def

case class CounterIter(counter:Primitive, offset:Option[Int])(implicit design:PIRDesign) extends Def 
case class OpDef(op:Op, inputs:List[Def])(implicit design:PIRDesign) extends StageDef
case class ReduceAccumOp(op:Op, input:Def, accum:Def)(implicit design:PIRDesign) extends StageDef
// Lowered
case class ReduceOp(op:Op, input:Def)(implicit design:PIRDesign) extends StageDef
case class AccumOp(op:Op, input:Def/*, accum:Def*/)(implicit design:PIRDesign) extends StageDef

// IR's doesn't matter in spatial. such as valid for counters. Should be dead code eliminated
case class DummyOp()(implicit design:PIRDesign) extends Def
case class Const[T](value:T)(implicit design:PIRDesign) extends Def

trait GlobalIO extends Def
trait GlobalInput extends GlobalIO { val globalOutput:GlobalOutput }
object GlobalInput {
  def unapply(n:Any):Option[GlobalOutput] = n match {
    case ValidGlobalInput(gout) => Some(gout)
    case ReadyValidGlobalInput(gout, ready) => Some(gout)
    case _ => None
  }
}
case class ValidGlobalInput(globalOutput:GlobalOutput)(implicit design:PIRDesign) extends GlobalInput
case class ReadyValidGlobalInput(globalOutput:GlobalOutput, ready:ControlNode)(implicit design:PIRDesign) extends GlobalInput
case class DataValid(globalInput:GlobalInput)(implicit design:PIRDesign) extends ControlNode // If DataValid is enqEn of EnabledStoreMem, the valid goes along with data

case class GlobalOutput(data:Def, valid:ControlNode)(implicit design:PIRDesign) extends GlobalIO
case class DataReady(globalOutput:GlobalOutput)(implicit design:PIRDesign) extends ControlNode // If DataValid is enqEn of EnabledStoreMem, the valid goes along with data

case class CountAck(ack:Def)(implicit design:PIRDesign) extends ControlNode
