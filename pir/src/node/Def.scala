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

trait PIRDef {
  def isReduceOp(n:PIRNode) = n match {
    case n:ReduceAccumOp => true
    case n:ReduceOp => true
    case n => false
  }
}
