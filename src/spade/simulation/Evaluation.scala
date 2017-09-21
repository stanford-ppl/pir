package spade.simulation

import spade._
import spade.util._

import pirc.enums._
import pirc.exceptions._

import scala.language.implicitConversions

trait Evaluation {
  def unwrap(x:Any, op:Op)(implicit sim:Simulator):Any = (x,op) match {
    case (x:SingleValue, op:Op) => unwrap(x.update.value, op)
    case (Some(x), op:Op) => unwrap(x, op)
    case (x:Int, op:FltOp) => x.toFloat
    case (x:Int, op:BitOp) => x > 0 
    case (x, op) => x
  }
  def eval(op:Op1, a:Any)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    (op,unwrap(a, op)) match {
      case (op:FixOp  , a:Int     ) => Some(op.eval(a))
      case (op:FltOp  , a:Float   ) => Some(op.eval(a))
      case (op:BitOp  , a:Boolean ) => Some(op.eval(a))
      case (op@Bypass , a:Float   ) => Some(op.eval(a))
      case (op@Bypass , a:Int     ) => Some(op.eval(a))
      case (op@Bypass , a:Boolean ) => Some(op.eval(a))
      case (_         , None      ) => None
      case (op        , a         ) => 
        throw PIRException(s"Don't know how to eval $op for ins=${quote(a)}")
    }
  }
  def eval(op:Op2, a:Any, b:Any)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    (op,unwrap(a, op),unwrap(b, op)) match {
      case (op:FixOp , a:Int    , b:Int      ) => Some(op.eval(a, b))
      case (op:FltOp , a:Float  , b:Float    ) => Some(op.eval(a, b))
      case (op:BitOp , a:Boolean, b:Boolean  ) => Some(op.eval(a, b))
      case (op@BitOr , a:Boolean, None       ) => Some(a)
      case (op@BitOr , None     , b:Boolean  ) => Some(b)
      case (op@BitAnd, _        , false      ) => Some(false)
      case (op@BitAnd, false    , _          ) => Some(false)
      case (_        , None     , _          ) => None
      case (_        , _        , None       ) => None
      case (op       , a        , b          ) => 
        throw PIRException(s"Don't know how to eval $op for ins=[${quote(a)},${quote(b)}]")
    }
  }
  def eval(op:Op3, a:Any, b:Any, c:Any)(implicit sim:Simulator):Option[AnyVal] = {
    import sim.util._
    (op,unwrap(a, op),unwrap(b, op),unwrap(c, op)) match {
      case (op@Mux, true     , b:Int    , _        ) => Some(b)
      case (op@Mux, false    , _        , c:Int    ) => Some(c)
      case (op@Mux, true     , b:Float  , _        ) => Some(b)
      case (op@Mux, false    , _        , c:Float  ) => Some(c)
      case (op@Mux, true     , b:Boolean, _        ) => Some(b)
      case (op@Mux, false    , _        , c:Boolean) => Some(c)
      case (op@Mux, a:Boolean, b:Int    , c:Int    ) => Some(op.eval(a, b, c))
      case (op@Mux, a:Boolean, b:Float  , c:Float  ) => Some(op.eval(a, b, c))
      case (op@Mux, a:Boolean, b:Boolean, c:Boolean) => Some(op.eval(a, b, c))
      case (_     , None     , _        , _        ) => None
      case (_     , _        , None     , _        ) => None
      case (_     , _        , _        , None     ) => None
      case (op    , a        , b        , c        ) => 
        throw PIRException(s"Don't know how to eval $op for ins=[${quote(a)},${quote(b)},${quote(c)}]")
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

  implicit def sv_to_opt(sv:SingleValue)(implicit sim:Simulator):Option[AnyVal] = sv.update.value
  implicit def int_to_opt(int:Int):Option[AnyVal] = Some(int)
  implicit def float_to_opt(f:Float):Option[AnyVal] = Some(f)
  implicit def bool_to_opt(b:Boolean):Option[AnyVal] = Some(b)
  implicit def av_to_opt(a:AnyVal):Option[AnyVal] = Some(a)
  def isHigh(v:Option[AnyVal]):Option[Boolean] = v.map { 
    case v:Boolean => v
    case v => throw new Exception(s"Don't know how to check isHigh for $v")
  }
  def isLow(v:Option[AnyVal]):Option[Boolean] = v.map { 
    case v:Boolean => !v
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
      case (cond:Boolean, func) => (Some(cond), func)
      case (cond:Option[_], func) => (cond.asInstanceOf[Option[AnyVal]], func)
    }
    if (sim.inRegistration) {
      matchPairs.foreach { case (_, func) => func() }
      defaultFunc
    } else {
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
  }
  def IfElse(cond:Option[AnyVal])(trueFunc: => Unit)(falseFunc: => Unit)(implicit sim:Simulator) = {
    if (sim.inRegistration) { trueFunc; falseFunc }
    else {
      isHigh(cond).foreach { 
        case true => trueFunc
        case false => falseFunc
      }
    }
  }
  def If(cond:Option[AnyVal])(trueFunc: => Unit)(implicit sim:Simulator):Unit = {
    if (sim.inRegistration) { trueFunc }
    else {
      isHigh(cond).foreach {
        case true => trueFunc
        case false =>
      }
    }
  }
}
