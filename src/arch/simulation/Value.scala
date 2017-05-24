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

trait Val[P<:PortType]{ self:IO[P, Module] =>
  private lazy val _values = ListBuffer[P]() // from current to previous value
  def values = _values.toList
  def setPrev(pv:SingleValue, nv:SingleValue)(implicit sim:Simulator):Unit = {
    pv.next = Some(nv)
    nv.prev = Some(pv)
    pv.set { pv => pv copy nv }
  }
  def setPrev(pv:BusValue, nv:BusValue)(implicit sim:Simulator):Unit = {
    (pv.value, nv.value).zipped.foreach { case (pv, nv) => setPrev(pv, nv) }
  }
  def setPrev(pv:Value, nv:Value)(implicit sim:Simulator):Unit = {
    (pv, nv) match {
      case (pv:SingleValue, nv:SingleValue) => setPrev(pv, nv)
      case (pv:BusValue, nv:BusValue) => setPrev(pv, nv)
    }
  }
  def vAt(idx:Int)(implicit sim:Simulator):P = {
    assert(tp.io == this, s"$tp.io != ${this}")
    while (values.size<=idx) {
      val v = tp.clone(s"${tp.typeStr}v${values.size}")
      assert(v.io == this)
      values.lastOption.foreach { nv => setPrev(v, nv) }
      _values += v
    }
    values(idx)
  }
  def v(implicit sim:Simulator):P = vAt(0)
  def pv(implicit sim:Simulator):P = vAt(1)
  //def ev(implicit sim:Simulator):P = vAt(0).update
  //def epv(implicit sim:Simulator):P = vAt(1).update
  //def vs:String = s"${value.s}"
  //def pvs:String = s"${prevValue.s}"

  def changed(implicit sim:Simulator):Boolean = v.value != pv.value

  def update(implicit sim:Simulator):Unit = { 
    assert(sim.inSimulation)
    //if (v.isDefined) sim.emitBlock(s"UpdateIO ${sim.quote(this)}") { v.update }
    if (v.isDefined) v.update
  }

  def clearUpdate(implicit sim:Simulator) = {
    values.foreach(_.clearUpdate)
  }

  def := (o: => IO[_<:PortType, Module])(implicit sim:Simulator):Unit = {
    v := o.v
  }

  def :== (o: => IO[_<:PortType, Module])(implicit sim:Simulator):Unit = {
    v := o.pv
  }

}

trait Value extends Node with Evaluation { self:PortType =>
  type V
  def value:V
  def s:String
  def asBit:BitValue = this.asInstanceOf[BitValue]
  def asWord:WordValue = {
    this match {
      case v:WordValue => v
      case _ => throw new Exception(s"${io}'s value cannot be casted to WordValue")
    }
  }
  def asBus:BusValue = this.asInstanceOf[BusValue]
  var parent:Option[Value] = None

  def isDefined:Boolean
  def updated:Boolean
  final def update(implicit sim:Simulator):this.type = {
    if (updated || !isDefined) return this
    prevUpdate
    mainUpdate
    //postUpdate // allow cyclic update on previous value
    this
  }
  def prevUpdate(implicit sim:Simulator):Unit = { prev.foreach(_.update) }
  def mainUpdate(implicit sim:Simulator):Unit
  def postUpdate(implicit sim:Simulator):Unit = { next.foreach(_.update) }
  var prev:Option[Value] = None
  var next:Option[Value] = None
  def clearUpdate(implicit sim:Simulator):Unit
  def := (other:Value)(implicit sim:Simulator):Unit
  def <<= (other:Value)(implicit sim:Simulator):Unit = { 
    sim.dprintln(s"${sim.quote(this)} <<= ${sim.quote(other)}")
    copy(other.update)
  }
  def copy (other:Value):Unit
  def set(f: this.type => Unit)(implicit sim:Simulator):Unit
}

trait Evaluation {
  def unwrap(x:Any)(implicit sim:Simulator):Any = x match {
    case x:SingleValue => unwrap(x.update.value)
    case Some(x) => unwrap(x)
    case x:Int => 
      x.toFloat
    case x => 
      x
  }
  def eval(op:Op, ins:Any*)(implicit sim:Simulator):Option[AnyVal] = {
    val inputs = ins.toList.map(unwrap)
    (inputs, op) match {
      case ((a:Float)::(b:Float)::_, FixAdd) => Some(a + b)
      case ((a:Float)::(b:Float)::_, FixSub) => Some(a - b)
      case ((a:Float)::(b:Float)::_, FixMul) => Some(a * b)

      case ((a:Float)::(b:Float)::_, FltAdd) => Some(a + b)
      case ((a:Float)::(b:Float)::_, FltSub) => Some(a - b)
      case ((a:Float)::(b:Float)::_, FltMul) => Some(a * b)
      case ((a:Float)::(b:Float)::_, FltGeq) => Some(a >= b)
      case ((a:Float)::(b:Float)::_, FltGt) => Some(a > b)

      case ((a:Boolean)::(b:Boolean)::_, BitAnd) => Some(a & b)
      case ((a:Boolean)::(b:Boolean)::_, BitOr) => Some(a | b)
      case ((a:Boolean)::(b:Boolean)::_, BitXnor) => Some(a == b)
      case ((a:Boolean)::(b:Boolean)::_, BitXor) => Some(a != b)
      case ((a:Boolean)::_, BitNot) => Some(!a)
      case (ins, op) if ins.contains(None) => return None 
      case (ins, op) =>
        throw PIRException(s"Don't know how to eval $op for ins=$ins")
    }
  }
  //def eval(op:Op)(ins:Any)(implicit sim:Simulator):Option[AnyVal] = {
    //(ins, op) match {
      //case ((Some(a:Float), Some(b:Float)), FixAdd) => Some(a + b)
      //case ((Some(a:Float), Some(b:Float)), FixSub) => Some(a - b)
      //case ((Some(a:Float), Some(b:Float)), FltAdd) => Some(a + b)
      //case ((Some(a:Float), Some(b:Float)), FltSub) => Some(a - b)
      //case ((Some(a:Float), Some(b:Float)), FltGeq) => Some(a >= b)
      //case ((Some(a:Float), Some(b:Float)), FltGt) => Some(a > b)
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

  implicit def wv_to_opt(wv:WordValue)(implicit sim:Simulator):Option[Float] = wv.update.value
  implicit def bv_to_opt(bv:BitValue)(implicit sim:Simulator):Option[Boolean] = bv.update.value
  implicit def int_to_opt(int:Int):Option[Float] = Some(int.toFloat)
  implicit def float_to_opt(f:Float):Option[Float] = Some(f.toFloat)
  implicit def bool_to_opt(b:Boolean):Option[Boolean] = Some(b)
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

trait SingleValue extends Value { self:PortType =>
  type E <: AnyVal
  type V = Option[E]
  var value:V = None
  def isVOrElse(x:Any)(matchFunc: V => Unit)(unmatchFunc: Any => Unit):Unit
  var _updated = false
  override def updated = _updated
  var func: Option[(this.type => Unit, String)] = None 
  def set(f: this.type => Unit)(implicit sim:Simulator):Unit = {
    if (next.isEmpty) assert(!sim.inSimulation)
    val stackTrace = getStackTrace(1, 20)
    if (func.isDefined) 
      warn(s"Reseting func of value $this of io ${io} in ${io.src} ${getStackTrace(4, 6)}")
    func = Some((f,stackTrace)) 
  }
  override def isDefined:Boolean = func.isDefined && next.fold(true) { _.isDefined }
  override def mainUpdate(implicit sim:Simulator):Unit = { 
    if (updated || !isDefined) return
    assert(_updated==false)
    _updated = true
    func.foreach { case (f, stackTrace) => 
      try {
        sim.emitBlock(s"UpdateValue #${sim.cycle} ${sim.quote(this)} n${id}", {
          f(this)
        }, s"UpdateValue #${sim.cycle} ${sim.quote(this)} n${id} ${value}")
      } catch {
        case e:Exception =>
          errmsg(e.toString)
          errmsg(e.getStackTrace.slice(0,5).mkString("\n"))
          errmsg(s"\nStaged trace for $this: ")
          errmsg(stackTrace)
          sys.exit()
      }
    }
  }
  override def clearUpdate(implicit sim:Simulator) = {
    if (isDefined && !updated) throw PIRException(s"${this} is not updated at #${sim.cycle}")
    _updated = false 
  }
  def := (other:Value)(implicit sim:Simulator):Unit = set { v => v <<= other }
  def := (other: => Option[AnyVal])(implicit sim:Simulator):Unit = set { v => v copy other }
  def <<= (other:Option[AnyVal]):Unit = copy(other) 
  def copy(other:Value):Unit = copy(other.asInstanceOf[SingleValue].value)
  def copy (other:Option[AnyVal]):Unit
}

trait BitValue extends SingleValue { self:Bit =>
  type E = Boolean
  def isVOrElse(x:Any)(matchFunc: V => Unit)(unmatchFunc: Any => Unit) = x match {
    case v:Option[_] if v.fold(true) { _.isInstanceOf[E] } => matchFunc(v.asInstanceOf[V])
    case v => unmatchFunc(v)
  }
  def copy (other:Option[AnyVal]) = value = other.asInstanceOf[V]
  def s:String = value match {
    case Some(true) => "1"
    case Some(false) => "0"
    case None => "x"
  }
  override def equals(that:Any):Boolean = {
    that match {
      case that: Bit => super.equals(that) && (this.value == that.value)
      case that => false
    }
  }

  def isHigh:V = value
  def isLow:V = value.map { v => !v } 
  def setHigh = value = Some(true)
  def setLow = value = Some(false)
  def & (vl:Any)(implicit sim:Simulator):V = eval(BitAnd, this, vl).asInstanceOf[V]
  def | (vl:Any)(implicit sim:Simulator):V = eval(BitOr, this, vl).asInstanceOf[V]
  def == (vl:Any)(implicit sim:Simulator):V = eval(BitXnor, this, vl).asInstanceOf[V]
  def != (vl:Any)(implicit sim:Simulator):V = eval(BitXor, this, vl).asInstanceOf[V]
  def not(implicit sim:Simulator):V = eval(BitNot, this).asInstanceOf[V]
}

trait WordValue extends SingleValue { self:Word =>
  type E = Float
  def isVOrElse(x:Any)(matchFunc: V => Unit)(unmatchFunc: Any => Unit) = x match {
    case v:Option[_] if v.fold(true) { _.isInstanceOf[E] } => matchFunc(v.asInstanceOf[V])
    case v => unmatchFunc(v)
  }
  def copy (other:Option[AnyVal]):Unit = value = other.asInstanceOf[V]
  def <<= (other:Int):Unit = <<=(Some(other.toFloat)) 
  def s:String = value match {
    case Some(v) => s"$v"
    case None => "x"
  }
  override def equals(that:Any):Boolean = {
    that match {
      case that: Bit => super.equals(that) && (this.value == that.value)
      case that => false
    }
  }

  def + (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltAdd, this, vl)
  def - (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltSub, this, vl)
  def * (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltMul, this, vl)
  def >= (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltGeq, this, vl)
  def > (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltGt, this, vl)
  def < (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltLt, this, vl)
}

trait BusValue extends Value { self:Bus =>
  type V = List[Value] 
  val value:V = List.tabulate(busWidth) { i => 
    val eval = elemTp.clone(s"${elemTp.typeStr}v[$i]")
    eval.parent = Some(this)
    eval
  }
  def s:String = value.map(_.s).mkString
  override def equals(that:Any):Boolean = {
    that match {
      case that: Bit => super.equals(that) && (this.value == that.value)
      case that => false
    }
  }
  def foreach(lambda:(Value, Int) => Unit):Unit = value.zipWithIndex.foreach { case (e, i) => lambda(e, i) }
  def head:Value = value.head

  override def isDefined:Boolean = value.exists(_.isDefined)
  override def updated = value.forall(_.updated) 
  override def mainUpdate(implicit sim:Simulator):Unit = {
    if (updated || !isDefined) return
    sim.emitBlock(s"UpdateValue $this") {
      value.foreach(_.update)
    }
  }
  override def clearUpdate(implicit sim:Simulator) = {
    value.foreach(_.clearUpdate)
  }
  override def := (other:Value)(implicit sim:Simulator):Unit = {
    other match {
      case other:SingleValue =>
        value.foreach { v => v := other }
      case other:BusValue =>
        (value, other.value).zipped.foreach { case (v, ov) => v := ov }
    }
  }
  override def copy (other:Value) = {
    other match {
      case other:SingleValue =>
        value.foreach { v => v copy other }
      case other:BusValue =>
        (value, other.value).zipped.foreach { case (v, ov) => v copy ov }
    }
  }
  def set(f: this.type => Unit)(implicit sim:Simulator):Unit = {
    throw new Exception(s"Cannot set bus value!")
  }
}

