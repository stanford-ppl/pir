package prism.enums

import prism._

sealed trait Op extends Enum

sealed trait Op1 extends Op
sealed trait Op2 extends Op
sealed trait Op3 extends Op
sealed trait Op4 extends Op

sealed trait CompOp extends Op

sealed trait FixOp extends Op
case object FixAdd extends FixOp             with Op2
case object FixSub extends FixOp             with Op2
case object FixMul extends FixOp             with Op2
case object FixDiv extends FixOp             with Op2
case object FixMin extends FixOp             with Op2
case object FixMax extends FixOp             with Op2
case object FixLt  extends FixOp with CompOp with Op2
case object FixLeq extends FixOp with CompOp with Op2
case object FixGt  extends FixOp with CompOp with Op2
case object FixGeq extends FixOp with CompOp with Op2
case object FixEql extends FixOp with CompOp with Op2
case object FixNeq extends FixOp with CompOp with Op2
case object FixMod extends FixOp             with Op2
case object FixSra extends FixOp             with Op2
case object FixSla extends FixOp             with Op2
case object FixUsla extends FixOp            with Op2
case object FixNeg extends FixOp             with Op1
case object FixRandom extends FixOp          with Op1
case object FixUnif extends FixOp            with Op1

sealed trait FltOp extends Op
case object FltAdd extends FltOp             with Op2
case object FltSub extends FltOp             with Op2
case object FltMul extends FltOp             with Op2
case object FltDiv extends FltOp             with Op2
case object FltMin extends FltOp             with Op2
case object FltMax extends FltOp             with Op2
case object FltLt  extends FltOp with CompOp with Op2
case object FltLeq extends FltOp with CompOp with Op2
case object FltGt  extends FltOp with CompOp with Op2
case object FltGeq extends FltOp with CompOp with Op2
case object FltEql extends FltOp with CompOp with Op2
case object FltNeq extends FltOp with CompOp with Op2
case object FltExp extends FltOp             with Op1
case object FltAbs extends FltOp             with Op1
case object FltLog extends FltOp             with Op1
case object FltSqr extends FltOp             with Op1
case object FltNeg extends FltOp             with Op1

sealed trait BitOp  extends Op
case object BitAnd  extends BitOp with Op2
case object BitOr   extends BitOp with Op2
case object BitNot  extends BitOp with Op1
case object BitXnor extends BitOp with Op2
case object BitXor  extends BitOp with Op2

case object MuxOp   extends Op with Op3
case object Bypass extends Op with Op1

trait Ops {
  val fixOps = List(FixAdd, FixSub, FixMul, FixDiv, FixMin, FixMax, FixLt, FixLeq, FixGt, FixGeq, FixEql, FixNeq, FixMod, FixSra, FixSla, FixUsla, FixNeg, FixRandom, FixUnif)
  val fltOps = List(FltAdd, FltSub, FltMul, FltDiv, FltMin, FltMax, FltLt, FltGt, FltGeq, FltEql, FltNeq, FltExp, FltAbs, FltLog, FltSqr, FltNeg)
  val bitOps = List(BitAnd, BitOr, BitNot, BitXnor, BitXor)
  val otherOps = List(MuxOp, Bypass)

  def ops = (fixOps ++ fltOps ++ bitOps ++ otherOps).toList
  def compOps = ops.collect {case op:CompOp => op}
  def fixCompOps = fixOps.collect {case op:CompOp => op}
  def fltCompOps = fltOps.collect {case op:CompOp => op}

  def convert(x:Any, op:Op):Any = (x,op) match {
    case (x:Int, op:FltOp) => x.toFloat
    case (x:Int, op:BitOp) => x > 0 
    case (x:Float, op:FixOp) => java.lang.Float.floatToIntBits(x)
    case (x, op) => x
  }

  def eval(op:Op, ins:Any*):Any = {
    (op, ins.toList.map(in => convert(in, op))) match {
      case (FixAdd   , (a:Int)::(b:Int)::_)                     => (a + b)
      case (FixSub   , (a:Int)::(b:Int)::_)                     => (a - b)
      case (FixMul   , (a:Int)::(b:Int)::_)                     => (a * b)
      case (FixDiv   , (a:Int)::(b:Int)::_)                     => (a / b)
      case (FixMin   , (a:Int)::(b:Int)::_)                     => (Math.min(a, b))
      case (FixMax   , (a:Int)::(b:Int)::_)                     => (Math.max(a, b))
      case (FixLt    , (a:Int)::(b:Int)::_)                     => (a < b)
      case (FixLeq   , (a:Int)::(b:Int)::_)                     => (a <= b)
      case (FixGt    , (a:Int)::(b:Int)::_)                     => (a > b)
      case (FixGeq   , (a:Int)::(b:Int)::_)                     => (a >= b)
      case (FixEql   , (a:Int)::(b:Int)::_)                     => (a == b)
      case (FixNeq   , (a:Int)::(b:Int)::_)                     => (a != b)
      case (FixMod   , (a:Int)::(b:Int)::_)                     => (a % b)
      case (FixSra   , (a:Int)::(b:Int)::_)                     => (a >> b)
      case (FixSla   , (a:Int)::(b:Int)::_)                     => (a << b)
      case (FixUsla  , (a:Int)::(b:Int)::_)                     => (a << b)
      case (FixNeg   , (a:Int)::_)                              => (-a)
      case (FixRandom, (a:Int)::_)                              => (0) //TODO
      case (FixUnif  , (a:Int)::_)                              => (0) //TODO

      case (FltAdd   , (a:Float)::(b:Float)::_)                 => (a + b)
      case (FltSub   , (a:Float)::(b:Float)::_)                 => (a - b)
      case (FltMul   , (a:Float)::(b:Float)::_)                 => (a * b)
      case (FltDiv   , (a:Float)::(b:Float)::_)                 => (a / b)
      case (FltMin   , (a:Float)::(b:Float)::_)                 => (Math.min(a , b))
      case (FltMax   , (a:Float)::(b:Float)::_)                 => (Math.max(a , b))
      case (FltLt    , (a:Float)::(b:Float)::_)                 => (a < b)
      case (FltLeq   , (a:Float)::(b:Float)::_)                 => (a <= b)
      case (FltGt    , (a:Float)::(b:Float)::_)                 => (a > b)
      case (FltGeq   , (a:Float)::(b:Float)::_)                 => (a >= b)
      case (FltEql   , (a:Float)::(b:Float)::_)                 => (a == b)
      case (FltNeq   , (a:Float)::(b:Float)::_)                 => (a != b)
      case (FltExp   , (a:Float)::(b:Float)::_)                 => (Math.exp(a))
      case (FltAbs   , (a:Float)::(b:Float)::_)                 => (Math.abs(a))
      case (FltLog   , (a:Float)::(b:Float)::_)                 => (Math.log(a))
      case (FltSqr   , (a:Float)::(b:Float)::_)                 => (Math.sqrt(a))
      case (FltNeg   , (a:Float)::(b:Float)::_)                 => (-a)

      case (BitAnd   , (a:Boolean)::(b:Boolean)::_)             => (a && b)
      case (BitAnd   , Some(false)::b::_)                       => Some(false)
      case (BitAnd   , a::Some(false)::_)                       => Some(false)
      case (BitOr    , (a:Boolean)::(b:Boolean)::_)             => (a || b)
      case (BitOr    , Some(true)::b::_)                        => Some(true)
      case (BitOr    , a::Some(true)::_)                        => Some(true)
      case (BitNot   , (a:Boolean)::(b:Boolean)::_)             => (!a)
      case (BitXnor  , (a:Boolean)::(b:Boolean)::_)             => (a == b)
      case (BitXor   , (a:Boolean)::(b:Boolean)::_)             => (a != b)

      case (Bypass   , a::_)                                    => a
      case (MuxOp    , true::a::b::_)                           => a
      case (MuxOp    , false::a::b::_)                          => b
      case (MuxOp    , Some(true)::a::b::_)                     => a
      case (MuxOp    , Some(false)::a::b::_)                    => b
      case (MuxOp    , (s:Int)::a::b::_) if s==0                => a
      case (MuxOp    , (s:Int)::a::b::_) if s>0                 => b
      case (MuxOp    , Some(s:Int)::a::b::_) if s==0            => a
      case (MuxOp    , Some(s:Int)::a::b::_) if s>0             => b

      case (op:Op1   , Some(a)::_)                              => Some(eval(op, List(a)))
      case (op:Op2   , Some(a)::Some(b)::_)                     => Some(eval(op, List(a,b)))
      case (op:Op3   , Some(a)::Some(b)::Some(c)::_)            => Some(eval(op, List(a,b,c)))
      case (op:Op1   , a::_) if List(a).exists(_ == None)       => None
      case (op:Op2   , a::b::_) if List(a).exists(_ == None)    => None
      case (op:Op3   , a::b::c::_) if List(a).exists(_ == None) => None
      case (op, ins) => throw PIRException(s"Don't know how to evaluate $op ins=$ins")
    }
  }
}
