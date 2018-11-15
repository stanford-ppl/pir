package spade
package param

import prism.util._

sealed trait Opcode extends Enum

sealed trait Op1 extends Opcode
sealed trait Op2 extends Opcode
sealed trait Op3 extends Opcode
sealed trait Op4 extends Opcode

sealed trait FixOp extends Opcode
case object FixAdd extends FixOp        with Op2
case object FixSub extends FixOp        with Op2
case object FixMul extends FixOp        with Op2
case object FixDiv extends FixOp        with Op2
case object FixMin extends FixOp        with Op2
case object FixMax extends FixOp        with Op2
case object FixLt  extends FixOp        with Op2
case object FixLeq extends FixOp        with Op2
case object FixGt  extends FixOp        with Op2
case object FixGeq extends FixOp        with Op2
case object FixEql extends FixOp        with Op2
case object FixNeq extends FixOp        with Op2
case object FixMod extends FixOp        with Op2
case object FixSRA extends FixOp        with Op2
case object FixSLA extends FixOp        with Op2
case object FixUsla extends FixOp       with Op2
case object FixAnd extends FixOp        with Op2
case object FixNeg extends FixOp        with Op1
case object FixRandom extends FixOp     with Op1
case object FixUnif extends FixOp       with Op1
case object FixAbs extends FixOp        with Op1

sealed trait FltOp extends Opcode
case object FltAdd extends FltOp        with Op2
case object FltSub extends FltOp        with Op2
case object FltMul extends FltOp        with Op2
case object FixFMA extends FltOp        with Op2
case object FltDiv extends FltOp        with Op2
case object FltMin extends FltOp        with Op2
case object FltMax extends FltOp        with Op2
case object FltLt  extends FltOp        with Op2
case object FltLeq extends FltOp        with Op2
case object FltGt  extends FltOp        with Op2
case object FltGeq extends FltOp        with Op2
case object FltEql extends FltOp        with Op2
case object FltNeq extends FltOp        with Op2
case object FltExp extends FltOp        with Op1
case object FltAbs extends FltOp        with Op1
case object FltLog extends FltOp        with Op1
case object FltSqr extends FltOp        with Op1
case object FltNeg extends FltOp        with Op1

sealed trait BitOp  extends Opcode
case object BitAnd  extends BitOp       with Op2
case object BitOr   extends BitOp       with Op2
case object BitNot  extends BitOp       with Op1
case object BitXnor extends BitOp       with Op2
case object BitXor  extends BitOp       with Op2

case object Mux   extends Opcode      with Op3
case object FltToFix extends Opcode with Op4
case object FltToFlt extends Opcode with Op4
case object FixToFlt extends Opcode with Op3
case object FixToFix extends Opcode with Op3

trait Ops {
  val fixOps:Set[Opcode] = Set(FixAdd, FixSub, FixMul, FixDiv, FixMin, FixMax, FixLt, FixLeq, FixGt, FixGeq, FixEql, FixNeq, FixMod, FixSRA, FixSLA, FixUsla, FixNeg, FixRandom, FixUnif, FixAbs, FixFMA, FixAnd)
  val fltOps:Set[Opcode] = Set(FltAdd, FltSub, FltMul, FltDiv, FltMin, FltMax, FltLt, FltLeq, FltGt, FltGeq, FltEql, FltNeq, FltExp, FltAbs, FltLog, FltSqr, FltNeg)
  val bitOps:Set[Opcode] = Set(BitAnd, BitOr, BitNot, BitXnor, BitXor)
  val otherOps:Set[Opcode] = Set(Mux, FixToFlt, FltToFix)

  val noFltOps:Set[Opcode] = fixOps ++ bitOps ++ otherOps

  val allOps:Set[Opcode] = fixOps ++ fltOps ++ bitOps ++ otherOps

  object ToInt {
    def unapply(x:Any):Option[Int] = x match {
      case x:Int => Some(x)
      case x:Float => Some(java.lang.Float.floatToIntBits(x))
      case _ => None
    }
  }
  object ToFloat {
    def unapply(x:Any):Option[Float] = x match {
      case x:Float => Some(x)
      case x:Int => Some(x.toFloat)
      case _ => None
    }
  }
  object ToBool {
    def unapply(x:Any):Option[Boolean] = x match {
      case x:Boolean => Some(x)
      case x:Int => Some(x > 0)
      case _ => None
    }
  }

  def eval(op:Opcode, ins:List[Any]):Any = {
    (op, ins) match {
      case (FixAdd   , ToInt(a)::ToInt(b)::_)     => (a + b)
      case (FixSub   , ToInt(a)::ToInt(b)::_)     => (a - b)
      case (FixMul   , ToInt(a)::ToInt(b)::_)     => (a * b)
      case (FixDiv   , ToInt(a)::ToInt(b)::_)     => (a / b)
      case (FixMin   , ToInt(a)::ToInt(b)::_)     => (Math.min(a, b))
      case (FixMax   , ToInt(a)::ToInt(b)::_)     => (Math.max(a, b))
      case (FixLt    , ToInt(a)::ToInt(b)::_)     => (a < b)
      case (FixLeq   , ToInt(a)::ToInt(b)::_)     => (a <= b)
      case (FixGt    , ToInt(a)::ToInt(b)::_)     => (a > b)
      case (FixGeq   , ToInt(a)::ToInt(b)::_)     => (a >= b)
      case (FixEql   , ToInt(a)::ToInt(b)::_)     => (a == b)
      case (FixNeq   , ToInt(a)::ToInt(b)::_)     => (a != b)
      case (FixMod   , ToInt(a)::ToInt(b)::_)     => (a % b)
      case (FixSRA   , ToInt(a)::ToInt(b)::_)     => (a >> b)
      case (FixSLA   , ToInt(a)::ToInt(b)::_)     => (a << b)
      case (FixUsla  , ToInt(a)::ToInt(b)::_)     => (a << b)
      case (FixNeg   , ToInt(a)::_)               => (-a)
      case (FixRandom, ToInt(a)::_)               => (0) //TODO
      case (FixUnif  , ToInt(a)::_)               => (0) //TODO

      case (FltAdd   , ToFloat(a)::ToFloat(b)::_) => (a + b)
      case (FltSub   , ToFloat(a)::ToFloat(b)::_) => (a - b)
      case (FltMul   , ToFloat(a)::ToFloat(b)::_) => (a * b)
      case (FltDiv   , ToFloat(a)::ToFloat(b)::_) => (a / b)
      case (FltMin   , ToFloat(a)::ToFloat(b)::_) => (Math.min(a , b))
      case (FltMax   , ToFloat(a)::ToFloat(b)::_) => (Math.max(a , b))
      case (FltLt    , ToFloat(a)::ToFloat(b)::_) => (a < b)
      case (FltLeq   , ToFloat(a)::ToFloat(b)::_) => (a <= b)
      case (FltGt    , ToFloat(a)::ToFloat(b)::_) => (a > b)
      case (FltGeq   , ToFloat(a)::ToFloat(b)::_) => (a >= b)
      case (FltEql   , ToFloat(a)::ToFloat(b)::_) => (a == b)
      case (FltNeq   , ToFloat(a)::ToFloat(b)::_) => (a != b)
      case (FltExp   , ToFloat(a)::ToFloat(b)::_) => (Math.exp(a))
      case (FltAbs   , ToFloat(a)::ToFloat(b)::_) => (Math.abs(a))
      case (FltLog   , ToFloat(a)::ToFloat(b)::_) => (Math.log(a))
      case (FltSqr   , ToFloat(a)::ToFloat(b)::_) => (Math.sqrt(a))
      case (FltNeg   , ToFloat(a)::ToFloat(b)::_) => (-a)

      case (BitAnd   , ToBool(a)::ToBool(b)::_)   => (a && b)
      case (BitOr    , ToBool(a)::ToBool(b)::_)   => (a || b)
      case (BitNot   , ToBool(a)::ToBool(b)::_)   => (!a)
      case (BitXnor  , ToBool(a)::ToBool(b)::_)   => (a == b)
      case (BitXor   , ToBool(a)::ToBool(b)::_)   => (a != b)

      case (Mux    , ToBool(true)::a::b::_)     => a
      case (Mux    , ToBool(false)::a::b::_)    => b

      case (op, ins) => throw PIRException(s"Don't know how to evaluate $op ins=$ins")
    }
  }
}
