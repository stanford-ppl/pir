package spade
package param

import prism.util._

trait Ops extends Enumeration {
  sealed trait Opcode extends Val { def eval(ins:List[Any]):Option[Any] = None }
  sealed trait Op1 extends Opcode
  sealed trait Op2 extends Opcode
  sealed trait Op3 extends Opcode
  sealed trait Op4 extends Opcode
  sealed class FixOp extends Opcode
  sealed class FltOp extends Opcode
  sealed class BitOp  extends Opcode
  sealed class TextOp  extends Opcode
  sealed class OtherOp  extends Opcode

  private def m(x:Any)(f:PartialFunction[Any,Any]):Option[Any] = {
    x match {
      case x if f.isDefinedAt(x) => Some(f(x))
      case x => None
    }
  }

  private def tf(ins:List[Any])(lambda:PartialFunction[List[Any], Any]):Option[Any] = {
    var existsLong = false
    var existsFloat = false
    val tins = ins.map { 
      case Some(in:Int) => Some(in.toFloat)
      case Some(in:Long) => existsLong = true; Some(in.toFloat)
      case Some(in:Float) => existsFloat = true; Some(in)
      case in => in
    }
    val res = if (lambda.isDefinedAt(tins)) Some(lambda(tins)) else None
    res.map { 
      case res:Float =>
        if (existsFloat) res
        else if (existsLong) res.toLong
        else res.toInt
      case res => res
    }
  }

  val FixInv       = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case (a:Float)::Nil => 1/a } } // TODO: same as Recip?
  val FixNeg       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case (AsNumeric(a, n)) => n.negate(a) } }
  val FixAdd       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a + b } }
  val FixSub       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a - b } }
  val FixMul       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a * b } }
  val FixDiv       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a / b } }
  val FixRecip     = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => 1/a } } 
  val FixMod       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Int)::Some(b:Int)::Nil => a % b } }
  val FixAnd       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Int)::Some(b:Int)::Nil => a & b } }
  val FixOr        = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Int)::Some(b:Int)::Nil => a | b } }
  val FixLst       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a < b } }
  val FixLeq       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a <= b } }
  val FixXor       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Int)::Some(b:Int)::Nil => a ^ b } }
  val FixSLA       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Int)::Some(b:Int)::Nil => a << b } }
  val FixSRA       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Int)::Some(b:Int)::Nil => a >> b } }
  val FixSRU       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Int)::Some(b:Int)::Nil => a >> b } } // TODO
  val SatAdd       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a + b } }
  val SatSub       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a - b } }
  val SatMul       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a * b } }
  val SatDiv       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a / b } }
  val UnbMul       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a * b } }
  val UnbDiv       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a / b } }
  val UnbSatMul    = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a * b } }
  val UnbSatDiv    = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => a / b } }
  val FixNeq       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a)::Some(b)::Nil => a != b } }
  val FixEql       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a)::Some(b)::Nil => a == b } }
  val FixMax       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => Math.max(a,b) } }
  val FixMin       = new FixOp with Op2 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Nil => Math.min(a,b) } }
  val FixToFix     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixToFixSat     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixToFlt     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixToText     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixRandom    = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixAbs       = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case (AsNumeric(a, n)) => n.abs(a) } }
  val FixFloor     = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => a.floor } }
  val FixCeil      = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => a.ceil } }
  val FixLn        = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => Math.log(a) } }
  val FixExp       = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => Math.exp(a) } }
  val FixSqrt      = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => Math.sqrt(a) } }
  val FixSin       = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => Math.sin(a) } }
  val FixCos       = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => Math.cos(a) } }
  val FixTan       = new FixOp with Op1 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Nil => Math.tan(a) } }
  val FixSinh      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixCosh      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixTanh      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixAsin      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixAcos      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixAtan      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixPow       = new FixOp with Op2 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixFMA       = new FixOp with Op3 { override def eval(ins:List[Any]) = tf(ins) { case Some(a:Float)::Some(b:Float)::Some(c:Float)::Nil => a * b + c } }
  val FixRecipSqrt = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val FixSigmoid   = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = tf(ins) { case (a:Int)::Nil => -a } }*/
  val SelectNonNeg    = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Int)::Some(b:Int)::Nil => if (a < 0) b else a } }

  val FltIsPosInf  = new FltOp with Op1
  val FltIsNegInf  = new FltOp with Op1
  val FltIsNaN     = new FltOp with Op1
  val FltNeg       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => -a } }
  val FltAdd       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a + b } }
  val FltSub       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a - b } }
  val FltMul       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a * b } }
  val FltDiv       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a / b } }
  val FltMod       = new FltOp with Op2 
  val FltRecip     = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => 1/a } }
  val FltLst       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a < b } }
  val FltLeq       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a <= b } }
  val FltNeq       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a != b } }
  val FltEql       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => a == b } }
  val FltMax       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => Math.max(a,b) } }
  val FltMin       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Nil => Math.min(a,b) } }
  val FltToFlt     = new FltOp with Op1
  val FltToFix     = new FltOp with Op1
  val FltToText     = new FltOp with Op1
  val TextToFlt    = new FltOp with Op2 
  val FltRandom    = new FltOp with Op1 
  val FltAbs       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.abs(a) } }
  val FltFloor     = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => a.floor } }
  val FltCeil      = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => a.ceil } }
  val FltLn        = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.log(a) } }
  val FltExp       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.exp(a) } }
  val FltSqrt      = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.sqrt(a) } }
  val FltSin       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.sin(a) } }
  val FltCos       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.cos(a) } }
  val FltTan       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Nil => Math.tan(a) } }
  val FltSinh      = new FltOp with Op1 
  val FltCosh      = new FltOp with Op1 
  val FltTanh      = new FltOp with Op1 
  val FltAsin      = new FltOp with Op1 
  val FltAcos      = new FltOp with Op1 
  val FltAtan      = new FltOp with Op1 
  val FltPow       = new FltOp with Op2 
  val FltFMA       = new FltOp with Op3 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Float)::Some(b:Float)::Some(c:Float)::Nil => a * b + c } }
  val FltRecipSqrt = new FltOp with Op1 
  val FltSigmoid   = new FltOp with Op1 

  val Not       = new BitOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Boolean)::Nil => !a } }
  val And       = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Boolean)::Some(b:Boolean)::Nil => a & b } }
  val Or        = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Boolean)::Some(b:Boolean)::Nil => a | b } }
  val Xor       = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Boolean)::Some(b:Boolean)::Nil => a ^ b } }
  val Xnor      = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Boolean)::Some(b:Boolean)::Nil => a == b } }
  val BitRandom = new BitOp with Op1
  val BitToText = new BitOp with Op1

  val TextConcat = new TextOp with Opcode
  val TextEql = new TextOp with Op2
  val TextNeq = new TextOp with Op2
  val TextLength = new TextOp with Op1
  val TextApply = new TextOp with Op2
  val CharArrayToText = new TextOp with Opcode
  val GenericToText = new TextOp with Op1

  val BitsAsData = new OtherOp with Op1
  val Mux       = new OtherOp with Op3 { override def eval(ins:List[Any]) = m(ins) { case Some(a:Boolean)::Some(b)::Some(c)::Nil => if (a) b else c } }
  val OneHotMux = new OtherOp with Op2

  // Not sure why a val here will fail serialzation test
  def allOps:Set[Opcode] = values.toSet[Value].collect { case op:Opcode => op }
  val fixOps = allOps.collect { case op:FixOp => op }
  val fltOps = allOps.collect { case op:FltOp => op }
  val bitOps = allOps.collect { case op:BitOp => op }
  val noFltOps = allOps.filterNot { case op:FltOp => true; case _ => false }

} 

object AsNumeric {
  def unapply[T](x:Any):Option[(T, Numeric[T])] = (x match {
    case x:Int => Some((x, implicitly[Numeric[Int]]))
    case x:Long => Some((x, implicitly[Numeric[Long]]))
    case x:Float => Some((x, implicitly[Numeric[Float]]))
    case x:Double => Some((x, implicitly[Numeric[Double]]))
    case _ => None
  }).asInstanceOf[Option[(T, Numeric[T])]]
}

//object ToInt {
  //def unapply(x:Any):Option[Int] = x match {
    //case x:Int => Some(x)
    //case x:Long => Some(x.toInt)
    //case _ => None
  //}
//}
//object ToFloat {
  //def unapply(x:Any):Option[Float] = x match {
    //case x:Float => Some(x)
    //case x:Int => Some(x.toFloat)
    //case _ => None
  //}
//}
//object ToBool {
  //def unapply(x:Any):Option[Boolean] = x match {
    //case x:Boolean => Some(x)
    //case x:Int => Some(x > 0)
    //case _ => None
  //}
//}
