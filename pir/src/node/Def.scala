package pir.node

import pir._

import pirc._
import pirc.enums._

import scala.reflect._

abstract class Def(implicit design:PIR) extends Primitive with ComputeNode { self =>
  def depDefs:Set[Def] = deps.collect { case d:Def => d } 
  def localDepDefs = localDeps.collect { case d:Def => d } 
  def depedDefs:Set[Def] = depeds.collect { case d:Def => d } 
  def localDepedDefs = localDepeds.collect { case d:Def => d } 
}
object Def {
  def unapply[T<:PIRNode:ClassTag](x:T)(implicit design:Design):Option[(T, T)] = {
    x match {
      case n:T => Some((x, n.newInstance(n.values, staging=false)))
      case _ => None
    }
  }
}

trait StageDef extends Def

case class CounterIter(counter:Counter, offset:Option[Int])(implicit design:PIR) extends Def 
case class OpDef(op:Op, inputs:List[Def])(implicit design:PIR) extends StageDef
case class ReduceAccumOp(op:Op, input:Def, accum:Def)(implicit design:PIR) extends StageDef
// Lowered
case class ReduceOp(op:Op, input:Def)(implicit design:PIR) extends StageDef
case class AccumOp(op:Op, input:Def/*, accum:Def*/)(implicit design:PIR) extends StageDef

// IR's doesn't matter in spatial. such as valid for counters. Should be dead code eliminated
case class DummyOp()(implicit design:PIR) extends Def
case class Const[T<:AnyVal](value:T)(implicit design:PIR) extends Def

case class ArgInDef()(implicit design:PIR) extends Def

case class GlobalInput(globalOutput:GlobalOutput)(implicit design:PIR) extends Def
case class GlobalOutput(data:Def, valid:ControlNode)(implicit design:PIR) extends Def
case class DataValid(globalInput:GlobalInput)(implicit design:PIR) extends ControlNode // If DataValid is enqEn of EnabledStoreMem, the valid goes along with data
