package pir.plasticine.simulation

import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util.enums._
import pir.exceptions._
import pir.util.misc._
import pir.Config
import pir.plasticine.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
//import scala.reflect.{ClassTag, classTag}
import scala.reflect.runtime.universe._
import scala.language.implicitConversions

trait Evaluation {
  type WordTp = Float
  type BitTp = Boolean
  def unwrap(x:Any)(implicit sim:Simulator):Any = x match {
    case x:SingleValue => unwrap(x.update.value)
    case Some(x) => unwrap(x)
    case x:Int => x.toFloat
    case x => x
  }
  def eval(op:Op1, a:Any)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    (op,unwrap(a)) match {
      case (op:FixOp  , a:WordTp) => Some(op.eval(a))
      case (op:FltOp  , a:WordTp) => Some(op.eval(a))
      case (op:BitOp  , a:BitTp ) => Some(op.eval(a))
      case (op@Bypass , a:WordTp) => Some(op.eval(a))
      case (_         , None    ) => None
      case _ => throw PIRException(s"Don't know how to eval $op for ins=${quote(a)}")
    }
  }
  def eval(op:Op2, a:Any, b:Any)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    (op,unwrap(a),unwrap(b)) match {
      case (op:FixOp, a:WordTp, b:WordTp) => Some(op.eval(a, b))
      case (op:FltOp, a:WordTp, b:WordTp) => Some(op.eval(a, b))
      case (op:BitOp, a:BitTp , b:BitTp ) => Some(op.eval(a, b))
      case (op@BitOr, a:BitTp , None    ) => Some(a)
      case (op@BitOr, None    , b:BitTp ) => Some(b)
      case (_       , None    , _       ) => None
      case (_       , _       , None    ) => None
      case _ => throw PIRException(s"Don't know how to eval $op for ins=[${quote(a)},${quote(b)}]")
    }
  }
  def eval(op:Op3, a:Any, b:Any, c:Any)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    (op,unwrap(a),unwrap(b),unwrap(c)) match {
      case (_     , None    , _       , _       ) => None
      case (_     , _       , None    , _       ) => None
      case (_     , _       , _       , None    ) => None
      case (op@Mux, a:WordTp, b:WordTp, c:WordTp) => Some(op.eval(a, b, c))
      case _ => throw PIRException(s"Don't know how to eval $op for ins=[${quote(a)},${quote(b)},${quote(c)}]")
    }
  }
  def eval(op:Op, ins:Any*)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    val res = op match {
      case op:Op1 => eval(op, ins(0))
      case op:Op2 => eval(op, ins(0), ins(1))
      case op:Op3 => eval(op, ins(0), ins(1), ins(2))
    }
    //sim.emitBlock(s"eval($op)(${ins.map(quote).mkString(", ")})") {
      //dprintln(s"ins=[${ins.mkString(",")}] res=$res")
    //}
    res
  }
  class CurryHelper[T, Res](f: Seq[T] => Res, as: Seq[T]) {
    def myCurry() = this
    def apply(ts: T*) = new CurryHelper(f, as ++ ts)
    def apply(ts: Seq[T]) = f(as ++ ts)
  }
  implicit def toCurryHelper[T, Res](f: Seq[T] => Res) = new CurryHelper(f, IndexedSeq[T]())

  implicit def wv_to_opt(wv:WordValue)(implicit sim:Simulator):Option[WordTp] = wv.update.value
  implicit def bv_to_opt(bv:BitValue)(implicit sim:Simulator):Option[BitTp] = bv.update.value
  implicit def int_to_opt(int:Int):Option[WordTp] = Some(int.toFloat)
  implicit def float_to_opt(f:WordTp):Option[WordTp] = Some(f.toFloat)
  implicit def bool_to_opt(b:BitTp):Option[BitTp] = Some(b)
  //implicit def op_to_bit(op:Option[BitTp])(implicit spade:Spade):BitValue = { val b = Bit(); b.value=op; b }
  def isHigh(v:Option[AnyVal]):Option[BitTp] = v.map { 
    case v:BitTp => v
    case v => throw new Exception(s"Don't know how to check isHigh for $v")
  }
  def isLow(v:Option[AnyVal]):Option[BitTp] = v.map { 
    case v:BitTp => !v
    case v => throw new Exception(s"Don't know how to check isLow for $v")
  }
  //def IfElse[T](cond:Option[AnyVal])(trueFunc: => T)(falseFunc: => T)(implicit ev:TypeTag[T]):T = {
    //typeOf[T] match {
      //case t if t =:= typeOf[Unit] => 
        //isHigh(cond).foreach { 
          //case true => trueFunc
          //case false => falseFunc
        //}.asInstanceOf[T]
      //case t if t <:< typeOf[Option[_]] => 
        //isHigh(cond).flatMap { 
          //case true => trueFunc.asInstanceOf[Option[_]]
          //case false => falseFunc.asInstanceOf[Option[_]]
        //}.asInstanceOf[T]
    //}
  //}

  def Match(matches:(Any, () => Unit)*)(defaultFunc: => Unit)(implicit sim:Simulator):Unit = {
    var trigDefault = false
    val matchPairs:Seq[(Option[AnyVal], () => Unit)] = matches.map { 
      case (cond:SingleValue, func) => (cond.update.value, func)
      case (cond:BitTp, func) => (Some(cond), func)
      case (cond:Option[_], func) => (cond.asInstanceOf[Option[AnyVal]], func)
    }
    sim.dprintln(s"matchpairs: ${matchPairs.map(_._1)}")
    matchPairs.foreach { case (cond, func) =>
      cond.foreach { 
        case true => 
          trigDefault = false
          func()
          return
        case false => trigDefault = true
      }
    }
    if (trigDefault) defaultFunc
  }
  def IfElse(cond:Option[AnyVal])(trueFunc: => Unit)(falseFunc: => Unit) = {
    isHigh(cond).foreach { 
      case true => trueFunc
      case false => falseFunc
    }
  }
  def If(cond:Option[AnyVal])(trueFunc: => Unit):Unit = {
    isHigh(cond).foreach {
      case true => trueFunc
      case false =>
    }
  }
}
