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

  /*
   * @param input list of input operands. Each operand can be either a list or an element. If there
   * are multiple lists in operands, they must match in dimension. Element input will be broadcast
   * to all elements in list inputs.
   * @param f function taking a list of element inputs (same size as input) and produce element
   * output
   * @return an element output produced by f all inputs are elements, or a list of elements
   * by broadcast all single element to list inputs and evaluate f for each sets of inputs.
   * */
  private def m(x:List[Any])(f:PartialFunction[Any,Any]):Option[Any] = {
    val vecs = x.flatMap { case Literal(vs:List[_]) if vs.size > 1 => Some(vs.size); case _ => None }
    val vec = assertIdentical(vecs, s"input vec $x").getOrElse(1)
    val vres = List.tabulate(vec) { v =>
      val ins = x.map { 
        case Literal(vs:List[_]) if vs.size > 1 => Literal(vs(v))
        case Literal(vs::Nil) => Literal(vs)
        case exp => exp
      }
      ins match {
        case x if f.isDefinedAt(x) => Some(f(x))
        case x => None
      }
    }
    if (vres.size == 1) vres.head
    else {
      val const = vres.collect { case Some(c) => c }
      if (const.size != vec) {
        None
      } else {
        val (lit,nonlit) = const.partition { case Literal(c) => true; case _ => false }
        if (lit.size == vec) {
          Some(Literal(lit.map { case Literal(c) => c }))
        } else if (nonlit.size == vec) {
          val values = nonlit.toSet
          if (values.size == 1) Some(values.head) else None
        } else { // Both literal and non literal
          None
        }
      }
    }
  }

  lazy val castNum = new Numeric[Any] {
    def plus(x: Any, y: Any): Any = FixAdd.eval(List(x,y))
    def minus(x: Any, y: Any): Any = FixSub.eval(List(x,y))
    def times(x: Any, y: Any): Any = FixMul.eval(List(x,y))
    def negate(x: Any): Any = FixNeg.eval(List(x))
    def fromInt(x: Int): Any = x
    def toInt(x: Any): Int = err(s"Cannot convert to Int from internal vec const type")
    def toLong(x: Any): Long = err(s"Cannot convert to Long from internal vec const type")
    def toFloat(x: Any): Float = err(s"Cannot convert to Float from internal vec const type")
    def toDouble(x: Any): Double = err(s"Cannot convert to Double from internal vec const type")
    def compare(x: Any, y: Any): Int = err(s"Cannot compare internal vec const type")
  }

  val FixInv       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case (a:Float)::Nil => Literal(1/a) } } // TODO: same as Recip?
  val FixNeg       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Num1(n,a) => Literal(n.negate(a.as)) } }
  val FixAdd       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.plus(a.as,b.as))
                                                                                      case Literal(0)::b::Nil => b
                                                                                      case a::Literal(0)::Nil => a} }
  val FixSub       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.minus(a.as,b.as)) } }
  val FixMul       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.times(a.as,b.as)) } }
  val FixDiv       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.div(a,b)) } }
  val FixRecip     = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Num1(n,a) => Literal(n.div(n.fromInt(1),a)) } } 
  val FixMod       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a % b) } }
  val FixAnd       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a & b) } }
  val FixOr        = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a | b)
                                                                                      case a::Literal(0)::Nil => a
                                                                                      case Literal(0)::b::Nil => b } }
  val FixLst       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.lt(a.as,b.as)) } }
  val FixLeq       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.lteq(a.as,b.as)) } }
  val FixXor       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a ^ b) } }
  val FixSLA       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a << b) } }
  val FixSRA       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a >> b) } }
  val FixDivSRA    = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a >> b) } }
  val FixSRU       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(a >> b) } } // TODO
  val SatAdd       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.plus(a.as,b.as)) } }
  val SatSub       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.minus(a.as,b.as)) } }
  val SatMul       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.times(a.as,b.as)) } }
  val SatDiv       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.div(a,b)) } }
  val UnbMul       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.times(a.as,b.as)) } }
  val UnbDiv       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.div(a,b)) } }
  val UnbSatMul    = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.times(a.as,b.as)) } }
  val UnbSatDiv    = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.div(a,b)) } }
  val FixNeq       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a)::Literal(b)::Nil => Literal(a != b) } }
  val FixEql       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a)::Literal(b)::Nil => Literal(a == b) } }
  val FixMax       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.max(a.as,b.as)) } }
  val FixMin       = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Num2(n,a,b) => Literal(n.min(a.as,b.as)) } }
  val FixToFix     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixToFixSat     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixToFlt     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixToText     = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixRandom    = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixAbs       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Num1(n,a) => Literal(n.abs(a.as)) } }
  val FixFloor     = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(a.floor) } }
  val FixCeil      = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(a.ceil) } }
  val FixLn        = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.log(a)) } }
  val FixExp       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.exp(a)) } }
  val FixSqrt      = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.sqrt(a)) } }
  val FixSin       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.sin(a)) } }
  val FixCos       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.cos(a)) } }
  val FixTan       = new FixOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.tan(a)) } }
  val FixSinh      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixCosh      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixTanh      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixAsin      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixAcos      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixAtan      = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixPow       = new FixOp with Op2 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixFMA       = new FixOp with Op3 { override def eval(ins:List[Any]) = m(ins) { case Num3(n,a,b,c) => Literal(n.plus(n.times(a.as,b.as).as,c.as)) } }
  val FixRecipSqrt = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val FixSigmoid   = new FixOp with Op1 /*{ override def eval(ins:List[Any]) = m(ins) { case (a:Int)::Nil => Literal(-a) } }*/
  val SelectNonNeg    = new FixOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Int)::Literal(b:Int)::Nil => Literal(if (a < 0) b else a) } }

  val FltIsPosInf  = new FltOp with Op1
  val FltIsNegInf  = new FltOp with Op1
  val FltIsNaN     = new FltOp with Op1
  val FltNeg       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(-a) } }
  val FltAdd       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a + b) } }
  val FltSub       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a - b) } }
  val FltMul       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { 
    case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a * b)
    case Literal(1)::b::Nil => b 
    case a::Literal(1)::Nil => a
  }}
  val FltDiv       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a / b) } }
  val FltMod       = new FltOp with Op2 
  val FltRecip     = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(1/a) } }
  val FltLst       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a < b) } }
  val FltLeq       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a <= b) } }
  val FltNeq       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a != b) } }
  val FltEql       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(a == b) } }
  val FltMax       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(Math.max(a,b)) } }
  val FltMin       = new FltOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Nil => Literal(Math.min(a,b)) } }
  val FltToFlt     = new FltOp with Op1
  val FltToFix     = new FltOp with Op1
  val FltToText     = new FltOp with Op1
  val TextToFlt    = new FltOp with Op2 
  val FltRandom    = new FltOp with Op1 
  val FltAbs       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.abs(a)) } }
  val FltFloor     = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(a.floor) } }
  val FltCeil      = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(a.ceil) } }
  val FltLn        = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.log(a)) } }
  val FltExp       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.exp(a)) } }
  val FltSqrt      = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.sqrt(a)) } }
  val FltSin       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.sin(a)) } }
  val FltCos       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.cos(a)) } }
  val FltTan       = new FltOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Nil => Literal(Math.tan(a)) } }
  val FltSinh      = new FltOp with Op1 
  val FltCosh      = new FltOp with Op1 
  val FltTanh      = new FltOp with Op1 
  val FltAsin      = new FltOp with Op1 
  val FltAcos      = new FltOp with Op1 
  val FltAtan      = new FltOp with Op1 
  val FltPow       = new FltOp with Op2 
  val FltFMA       = new FltOp with Op3 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Float)::Literal(b:Float)::Literal(c:Float)::Nil => Literal(a * b + c) } }
  val FltRecipSqrt = new FltOp with Op1 
  val FltSigmoid   = new FltOp with Op1 

  val Not       = new BitOp with Op1 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Boolean)::Nil => Literal(!a) } }
  val And       = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Boolean)::Literal(b:Boolean)::Nil => Literal(a & b)
                                                                                   case a::Literal(true)::Nil => a
                                                                                   case Literal(true)::b::Nil => b } }
  val Or        = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Boolean)::Literal(b:Boolean)::Nil => Literal(a | b)
                                                                                   case a::Literal(false)::Nil => a
                                                                                   case Literal(false)::b::Nil => b } }
  val Xor       = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Boolean)::Literal(b:Boolean)::Nil => Literal(a ^ b) } }
  val Xnor      = new BitOp with Op2 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Boolean)::Literal(b:Boolean)::Nil => Literal(a == b) } }
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
  val Mux       = new OtherOp with Op3 { override def eval(ins:List[Any]) = m(ins) { case Literal(a:Boolean)::b::c::Nil => if (a) b else c } }
  val OneHotMux = new OtherOp with Op2

  // Not sure why a val here will fail serialzation test
  def allOps:Set[Opcode] = values.toSet[Value].collect { case op:Opcode => op }
  val fixOps = allOps.collect { case op:FixOp => op }
  val fltOps = allOps.collect { case op:FltOp => op }
  val bitOps = allOps.collect { case op:BitOp => op }
  val noFltOps = allOps.filterNot { case op:FltOp => true; case _ => false }

  implicit class NumOp[T](x:Numeric[T]) {
    def div(x:Any, y:Any) = (x,y) match {
      case (x:Int, y:Int) => x / y
      case (x:Long, y:Long) => x / y
      case (x:Float, y:Float) => x / y
      case (x:Double, y:Double) => x / y
      case _ => bug(s"Don't know how to compute div for $x and $y")
    }
  }

} 

object Num3 {
  def unapply(x:List[Any]):Option[(Numeric[_],Any,Any,Any)] = {
    x match {
      case Literal(ToInt(a))::Literal(ToInt(b))::Literal(ToInt(c))::Nil => Some(implicitly[Numeric[Int]], a, b, c)
      case Literal(ToLong(a))::Literal(ToLong(b))::Literal(ToLong(c))::Nil => Some(implicitly[Numeric[Long]], a, b, c)
      case Literal(ToFloat(a))::Literal(ToFloat(b))::Literal(ToFloat(c))::Nil => Some(implicitly[Numeric[Float]], a, b, c)
      case Literal(ToDouble(a))::Literal(ToDouble(b))::Literal(ToDouble(c))::Nil => Some(implicitly[Numeric[Double]], a, b, c)
      case _ => None
    }
  }
} 

case class Literal(x:Any)

object Num2 {
  def unapply(x:List[Any]):Option[(Numeric[_],Any,Any)] = {
    x match {
      case Literal(ToInt(a))::Literal(ToInt(b))::Nil => Some(implicitly[Numeric[Int]], a, b)
      case Literal(ToLong(a))::Literal(ToLong(b))::Nil => Some(implicitly[Numeric[Long]], a, b)
      case Literal(ToFloat(a))::Literal(ToFloat(b))::Nil => Some(implicitly[Numeric[Float]], a, b)
      case Literal(ToDouble(a))::Literal(ToDouble(b))::Nil => Some(implicitly[Numeric[Double]], a, b)
      case _ => None
    }
  }
} 

object Num1 {
  def unapply(x:List[Any]):Option[(Numeric[_],Any)] = {
    x match {
      case Literal(a:Int)::Nil => Some(implicitly[Numeric[Int]], a)
      case Literal(a:Long)::Nil => Some(implicitly[Numeric[Long]], a)
      case Literal(a:Float)::Nil => Some(implicitly[Numeric[Float]], a)
      case Literal(a:Double)::Nil => Some(implicitly[Numeric[Double]], a)
      case _ => None
    }
  }
}

object ToInt {
  def unapply(x:Int):Option[Int] = Some(x)
}
object ToLong {
  def unapply(x:Any):Option[Long] = x match {
    case x:Int => Some(x.toLong)
    case x:Long => Some(x)
    case _ => None
  }
}
object ToFloat {
  def unapply(x:Any):Option[Float] = x match {
    case x:Int => Some(x.toFloat)
    case x:Long => Some(x.toFloat)
    case x:Float => Some(x)
    case _ => None
  }
}
object ToDouble {
  def unapply(x:Any):Option[Double] = x match {
    case x:Int => Some(x.toDouble)
    case x:Long => Some(x.toDouble)
    case x:Float => Some(x)
    case x:Double => Some(x)
    case _ => None
  }
}
