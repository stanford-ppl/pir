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
    case x:Int => 
      x.toFloat
    case x => 
      x
  }
  def eval(op:Op, ins:Any*)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.{quote, dprintln}
    val inputs = ins.toList.map(unwrap)
    val res = (inputs, op) match {
      case ((a:WordTp)::(b:WordTp)::_, FixAdd)      => Some(a + b)
      case ((a:WordTp)::(b:WordTp)::_, FixSub)      => Some(a - b)
      case ((a:WordTp)::(b:WordTp)::_, FixMul)      => Some(a * b)

      case ((a:WordTp)::(b:WordTp)::_, FltAdd)      => Some(a + b)
      case ((a:WordTp)::(b:WordTp)::_, FltSub)      => Some(a - b)
      case ((a:WordTp)::(b:WordTp)::_, FltMul)      => Some(a * b)
      case ((a:WordTp)::(b:WordTp)::_, FltGeq)      => Some(a >= b)
      case ((a:WordTp)::(b:WordTp)::_, FltGt)       => Some(a > b)
      case ((a:WordTp)::(b:WordTp)::_, FltLeq)      => Some(a <= b)
      case ((a:WordTp)::(b:WordTp)::_, FltLt)       => Some(a < b)

      case ((a:BitTp)::(b:BitTp)::_, BitAnd)  => Some(a & b)
      case ((a:BitTp)::(b:BitTp)::_, BitOr)   => Some(a | b)
      case ((a:BitTp)::(b:BitTp)::_, BitXnor) => Some(a == b)
      case ((a:BitTp)::(b:BitTp)::_, BitXor)  => Some(a != b)
      case ((a:BitTp)::_, BitNot) => Some(!a)

      case ((a:WordTp)::_, Bypass)  => Some(a) 
      case ((a:BitTp)::_, Bypass)  => Some(a) 

      case (ins, op) if ins.contains(None) => None 
      case (ins, op) =>
        throw PIRException(s"Don't know how to eval $op for ins=$ins")
    }
    sim.emitBlock(s"eval($op)(${ins.map(quote).mkString(", ")})") {
      dprintln(s"inputs=[${inputs.mkString(",")}] res=$res")
    }
    res
  }
  //def eval(op:Op)(ins:Any)(implicit sim:Simulator):Option[AnyVal] = {
    //(ins, op) match {
      //case ((Some(a:WordTp), Some(b:WordTp)), FixAdd) => Some(a + b)
      //case ((Some(a:WordTp), Some(b:WordTp)), FixSub) => Some(a - b)
      //case ((Some(a:WordTp), Some(b:WordTp)), FltAdd) => Some(a + b)
      //case ((Some(a:WordTp), Some(b:WordTp)), FltSub) => Some(a - b)
      //case ((Some(a:WordTp), Some(b:WordTp)), FltGeq) => Some(a >= b)
      //case ((Some(a:WordTp), Some(b:WordTp)), FltGt) => Some(a > b)
      //case (None, op) => None
      //case ((None, _), op) => None
      //case ((_, None), op) => None
      //case ((a:SingleValue, b), op) => eval(op)((a.update.value, b))
      //case ((a, b:SingleValue), op) => eval(op)((a, b.update.value))
      //case (ins, op) =>
        //throw PIRException(s"Don't know how to eval $op for ins=$ins")
    //}
  //}
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
