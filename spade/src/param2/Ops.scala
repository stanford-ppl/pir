package spade
package param

import prism.util._

trait Ops extends Enumeration {
  sealed class Opcode extends Val
  sealed trait Op1 extends Opcode
  sealed trait Op2 extends Opcode
  sealed trait Op3 extends Opcode
  sealed trait Op4 extends Opcode
  sealed class FixOp extends Opcode
  sealed class FltOp extends Opcode
  sealed class BitOp  extends Opcode

  val FixInv       = new FixOp with Op1
  val FixNeg       = new FixOp with Op1
  val FixAdd       = new FixOp with Op2
  val FixSub       = new FixOp with Op2
  val FixMul       = new FixOp with Op2
  val FixDiv       = new FixOp with Op2
  val FixRecip     = new FixOp with Op1
  val FixMod       = new FixOp with Op2
  val FixAnd       = new FixOp with Op2
  val FixOr        = new FixOp with Op2
  val FixLst       = new FixOp with Op2
  val FixLeq       = new FixOp with Op2
  val FixXor       = new FixOp with Op2
  val FixSLA       = new FixOp with Op2
  val FixSRA       = new FixOp with Op2
  val FixSRU       = new FixOp with Op2
  val SatAdd       = new FixOp with Op2
  val SatSub       = new FixOp with Op2
  val SatMul       = new FixOp with Op2
  val SatDiv       = new FixOp with Op2
  val UnbMul       = new FixOp with Op2
  val UnbDiv       = new FixOp with Op2
  val UnbSatMul    = new FixOp with Op2
  val UnbSatDiv    = new FixOp with Op2
  val FixNeq       = new FixOp with Op2
  val FixEql       = new FixOp with Op2
  val FixMax       = new FixOp with Op2
  val FixMin       = new FixOp with Op2
  val FixToFix     = new FixOp with Op2
  val FixToFlt     = new FixOp with Op2
  val FixRandom    = new FixOp with Op1
  val FixAbs       = new FixOp with Op1
  val FixFloor     = new FixOp with Op1
  val FixCeil      = new FixOp with Op1
  val FixLn        = new FixOp with Op1
  val FixExp       = new FixOp with Op1
  val FixSqrt      = new FixOp with Op1
  val FixSin       = new FixOp with Op1
  val FixCos       = new FixOp with Op1
  val FixTan       = new FixOp with Op1
  val FixSinh      = new FixOp with Op1
  val FixCosh      = new FixOp with Op1
  val FixTanh      = new FixOp with Op1
  val FixAsin      = new FixOp with Op1
  val FixAcos      = new FixOp with Op1
  val FixAtan      = new FixOp with Op1
  val FixPow       = new FixOp with Op2
  val FixFMA       = new FixOp with Op3
  val FixRecipSqrt = new FixOp with Op1
  val FixSigmoid   = new FixOp with Op1

  val FltIsPosInf  = new FltOp with Op1
  val FltIsNegInf  = new FltOp with Op1
  val FltIsNaN     = new FltOp with Op1
  val FltNeg       = new FltOp with Op1
  val FltAdd       = new FltOp with Op2
  val FltSub       = new FltOp with Op2
  val FltMul       = new FltOp with Op2
  val FltDiv       = new FltOp with Op2
  val FltMod       = new FltOp with Op2
  val FltRecip     = new FltOp with Op1
  val FltLst       = new FltOp with Op2
  val FltLeq       = new FltOp with Op2
  val FltNeq       = new FltOp with Op2
  val FltEql       = new FltOp with Op2
  val FltMax       = new FltOp with Op2
  val FltMin       = new FltOp with Op2
  val FltToFlt     = new FltOp with Op2
  val FltToFix     = new FltOp with Op2
  val TextToFlt    = new FltOp with Op2
  val FltToText    = new FltOp with Op1
  val FltRandom    = new FltOp with Op1
  val FltAbs       = new FltOp with Op1
  val FltFloor     = new FltOp with Op1
  val FltCeil      = new FltOp with Op1
  val FltLn        = new FltOp with Op1
  val FltExp       = new FltOp with Op1
  val FltSqrt      = new FltOp with Op1
  val FltSin       = new FltOp with Op1
  val FltCos       = new FltOp with Op1
  val FltTan       = new FltOp with Op1
  val FltSinh      = new FltOp with Op1
  val FltCosh      = new FltOp with Op1
  val FltTanh      = new FltOp with Op1
  val FltAsin      = new FltOp with Op1
  val FltAcos      = new FltOp with Op1
  val FltAtan      = new FltOp with Op1
  val FltPow       = new FltOp with Op2
  val FltFMA       = new FltOp with Op3
  val FltRecipSqrt = new FltOp with Op1
  val FltSigmoid   = new FltOp with Op1

  val Not       = new BitOp with Op1
  val And       = new BitOp with Op2
  val Or        = new BitOp with Op2
  val Xor       = new BitOp with Op2
  val Xnor      = new BitOp with Op2
  val BitRandom = new BitOp with Op1

  val Mux = new Opcode with Op3
  val OneHotMux = new Opcode with Op2

  val allOps:Set[Opcode] = values.toSet[Value].map[Opcode, Set[Opcode]]{ _.asInstanceOf[Opcode] }
  val fixOps = allOps.collect { case op:FixOp => op }
  val fltOps = allOps.collect { case op:FltOp => op }
  val bitOps = allOps.collect { case op:BitOp => op }
  val noFltOps = allOps.filterNot { case op:FltOp => false; case _ => true }
}

//trait Ops {

  ////object ToInt {
    ////def unapply(x:Any):Option[Int] = x match {
      ////case x:Int => Some(x)
      ////case x:Float => Some(java.lang.Float.floatToIntBits(x))
      ////case _ => None
    ////}
  ////}
  ////object ToFloat {
    ////def unapply(x:Any):Option[Float] = x match {
      ////case x:Float => Some(x)
      ////case x:Int => Some(x.toFloat)
      ////case _ => None
    ////}
  ////}
  ////object ToBool {
    ////def unapply(x:Any):Option[Boolean] = x match {
      ////case x:Boolean => Some(x)
      ////case x:Int => Some(x > 0)
      ////case _ => None
    ////}
  ////}

  ////def eval(op:Opcode, ins:List[Any]):Any = {
    ////(op, ins) match {
      ////case (FixAdd   , ToInt(a)::ToInt(b)::_)     => (a + b)
      ////case (FixSub   , ToInt(a)::ToInt(b)::_)     => (a - b)
      ////case (FixMul   , ToInt(a)::ToInt(b)::_)     => (a * b)
      ////case (FixDiv   , ToInt(a)::ToInt(b)::_)     => (a / b)
      ////case (FixMin   , ToInt(a)::ToInt(b)::_)     => (Math.min(a, b))
      ////case (FixMax   , ToInt(a)::ToInt(b)::_)     => (Math.max(a, b))
      ////case (FixLst    , ToInt(a)::ToInt(b)::_)     => (a < b)
      ////case (FixLeq   , ToInt(a)::ToInt(b)::_)     => (a <= b)
      ////case (FixGt    , ToInt(a)::ToInt(b)::_)     => (a > b)
      ////case (FixGeq   , ToInt(a)::ToInt(b)::_)     => (a >= b)
      ////case (FixEql   , ToInt(a)::ToInt(b)::_)     => (a == b)
      ////case (FixNeq   , ToInt(a)::ToInt(b)::_)     => (a != b)
      ////case (FixMod   , ToInt(a)::ToInt(b)::_)     => (a % b)
      ////case (FixSRA   , ToInt(a)::ToInt(b)::_)     => (a >> b)
      ////case (FixSLA   , ToInt(a)::ToInt(b)::_)     => (a << b)
      ////case (FixUsla  , ToInt(a)::ToInt(b)::_)     => (a << b)
      ////case (FixNeg   , ToInt(a)::_)               => (-a)
      ////case (FixRandom, ToInt(a)::_)               => (0) //TODO
      ////case (FixUnif  , ToInt(a)::_)               => (0) //TODO

      ////case (FltAdd   , ToFloat(a)::ToFloat(b)::_) => (a + b)
      ////case (FltSub   , ToFloat(a)::ToFloat(b)::_) => (a - b)
      ////case (FltMul   , ToFloat(a)::ToFloat(b)::_) => (a * b)
      ////case (FltDiv   , ToFloat(a)::ToFloat(b)::_) => (a / b)
      ////case (FltMin   , ToFloat(a)::ToFloat(b)::_) => (Math.min(a , b))
      ////case (FltMax   , ToFloat(a)::ToFloat(b)::_) => (Math.max(a , b))
      ////case (FltLt    , ToFloat(a)::ToFloat(b)::_) => (a < b)
      ////case (FltLeq   , ToFloat(a)::ToFloat(b)::_) => (a <= b)
      ////case (FltGt    , ToFloat(a)::ToFloat(b)::_) => (a > b)
      ////case (FltGeq   , ToFloat(a)::ToFloat(b)::_) => (a >= b)
      ////case (FltEql   , ToFloat(a)::ToFloat(b)::_) => (a == b)
      ////case (FltNeq   , ToFloat(a)::ToFloat(b)::_) => (a != b)
      ////case (FltExp   , ToFloat(a)::ToFloat(b)::_) => (Math.exp(a))
      ////case (FltAbs   , ToFloat(a)::ToFloat(b)::_) => (Math.abs(a))
      ////case (FltLog   , ToFloat(a)::ToFloat(b)::_) => (Math.log(a))
      ////case (FltSqr   , ToFloat(a)::ToFloat(b)::_) => (Math.sqrt(a))
      ////case (FltNeg   , ToFloat(a)::ToFloat(b)::_) => (-a)

      ////case (BitAnd   , ToBool(a)::ToBool(b)::_)   => (a && b)
      ////case (BitOr    , ToBool(a)::ToBool(b)::_)   => (a || b)
      ////case (BitNot   , ToBool(a)::ToBool(b)::_)   => (!a)
      ////case (BitXnor  , ToBool(a)::ToBool(b)::_)   => (a == b)
      ////case (BitXor   , ToBool(a)::ToBool(b)::_)   => (a != b)

      ////case (Mux    , ToBool(true)::a::b::_)     => a
      ////case (Mux    , ToBool(false)::a::b::_)    => b

      ////case (op, ins) => throw PIRException(s"Don't know how to evaluate $op ins=$ins")
    ////}
  ////}
//}

