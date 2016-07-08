package dhdl.graph

sealed trait Op 
object Op {
  case object Mux extends Op
}

sealed trait FixOp extends Op 
object FixOp {
  case object FixAdd extends FixOp 
  case object FixSub extends FixOp 
  case object FixMul extends FixOp 
  case object FixDiv extends FixOp 
}

sealed trait FltOp extends Op 
object FltOp {
  case object FltAdd extends FltOp 
  case object FltSub extends FltOp 
  case object FltMul extends FltOp 
  case object FltDiv extends FltOp 
}
