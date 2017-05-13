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
import scala.reflect.{ClassTag, classTag}
import scala.reflect.runtime.universe._

trait Value { self:PortType =>
  type V
  def value:V
  def copy(other:Value):Unit
  def clonetp:this.type
  def s:String
  def asBit:BitValue = this.asInstanceOf[BitValue]
  def asWord:WordValue = this.asInstanceOf[WordValue]
}

trait SingleValue extends Value { self:PortType =>
  type E
  type V = Option[E]
  var value:V = None
  def := (v:V):Unit = { value = v}
}

trait BitValue extends SingleValue { self:Bit =>
  type E = Boolean
  override def copy(other:Value):Unit = { value = other.asInstanceOf[BitValue].value }
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
  def clonetp:this.type = Bit().asInstanceOf[this.type]

  def isHigh = value == Some(true)
  def isLow = value == Some(false)
}

trait WordValue extends SingleValue { self:Word =>
  type E = Float
  override def copy(other:Value):Unit = { value = other.asInstanceOf[WordValue].value }
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
  def clonetp:this.type = Word(wordWidth).asInstanceOf[this.type]

  def + (v:WordValue):V = eval(FltAdd)(value, v.value)
  def + (vl:V):V = eval(FltAdd)(value, vl)

  def eval(op:Op)(ins:Any):V = {
    (ins, op) match {
      case ((Some(a:Float), Some(b:Float)), FixAdd) => Some(a + b)
      case ((Some(a:Float), Some(b:Float)), FixSub) => Some(a - b)
      case ((Some(a:Float), Some(b:Float)), FltAdd) => Some(a + b)
      case ((Some(a:Float), Some(b:Float)), FltSub) => Some(a - b)
      case (None, op) => None
      case ((None, _), op) => None
      case ((_, None), op) => None
      case (_, _:BitOp) =>
        throw PIRException(s"Boolean Op to Float type op=$op ins=$ins")
      case (ins, op) =>
        throw PIRException(s"Don't know how to eval $op for ins=$ins")
    }
  }
}

trait BusValue extends Value { self:Bus =>
  type V = List[Value] 
  val value:V = List.fill(busWidth) (elemTp.clonetp)
  def s:String = value.map(_.s).mkString
  override def copy(other:Value):Unit = { 
    (value, other.asInstanceOf[Bus].value).zipped.foreach { case (v, ov) =>
      v.copy(ov)
    }
  }
  override def equals(that:Any):Boolean = {
    that match {
      case that: Bit => super.equals(that) && (this.value == that.value)
      case that => false
    }
  }
  def clonetp:this.type = Bus(busWidth, elemTp).asInstanceOf[this.type]

  def foreach(lambda:(Value, Int) => Unit):Unit = value.zipWithIndex.foreach { case (e, i) => lambda(e, i) }
  def head:Value = value.head
}

trait Val[P<:PortType]{ self:IO[P, Module] =>
  private val value:P = tp.clonetp
  private val prevValue:P = tp.clonetp
  def changed:Boolean = value != prevValue

  var func: Option[this.type => Unit] = None 
  def set(f: this.type => Unit):Unit = {
    if (func!=None) warn(s"Reseting func for ${this} of ${this.src} ${getStackTrace(4, 6)}")
    func = Some(f) 
  }
  var _updated = false
  def updated = _updated
  def clearUpdate(implicit sim:Simulator) = {
    if (_updated!=true) throw PIRException(s"${this} is not updated at #${sim.cycle}")
    _updated = false 
  }
  def update(implicit sim:Simulator):Unit = { 
    if (updated) return
    prevValue.copy(value)
    func.foreach { f => f(this) }
    //sim.dprintln(Config.debug && func.nonEmpty, s"#${sim.cycle} updating ${this} val:${value.value}")
    _updated = true
  }
  private def eval(implicit sim:Simulator):this.type = {
    update;
    this
  }

  def v:P = value
  def pv:P = prevValue
  def ev(implicit sim:Simulator):P = { eval; v }
  def epv(implicit sim:Simulator):P = { eval; pv }
  def vs:String = s"${value.s}"
  def pvs:String = s"${prevValue.s}"

  def := (o: => IO[_<:PortType, Module])(implicit sim:Simulator):Unit = {
    set{ v => v.v.copy(o.ev) }
  }

  def :== (o: => IO[_<:PortType, Module])(implicit sim:Simulator):Unit = {
    set{ v => v.v.copy(o.epv) }
  }

  //def isV(x:Val[_]) = x.tp==tp
  //def asV(x:Val[_]) = x.asInstanceOf[Val[P]] 

  //def + (v:Val[_]):V = eval(asV(v), FixAdd)
  //def - (v:Val[_]):V = eval(asV(v), FixSub)

  //def eval(v:Val[V], op:Op):V
  //def eval(op:Op):V

  //def toDecOp(op:Op)(ins:Any):Option[Float] = {
    //(ins, op) match {
      //case ((Some(a:Float), Some(b:Float)), FixAdd) => Some(a.toInt + b.toInt)
      //case ((Some(a:Float), Some(b:Float)), FixSub) => Some(a.toInt - b.toInt)
      //case ((Some(a:Float), Some(b:Float)), FltAdd) => Some(a.toFloat + b.toFloat)
      //case ((Some(a:Float), Some(b:Float)), FltSub) => Some(a.toFloat - b.toFloat)
      //case (None, op) => None
      //case ((None, _), op) => None
      //case ((_, None), op) => None
      //case (_, _:BitOp) =>
        //throw PIRException(s"Boolean Op to Float type op=$op ins=$ins")
      //case (ins, op) =>
        //throw PIRException(s"Don't know how to eval $op for ins=$ins")
    //}
  //}
  //def toBoolOp(op:Op)(ins:Any):Option[Boolean] = {
    //(ins, op) match {
      //case ((Some(a:Boolean), Some(b:Boolean)), BitAnd) => Some(a && b)
      //case ((Some(a:Boolean), Some(b:Boolean)), BitOr) => Some(a || b)
      //case (None, op) => None
      //case ((None, _), op) => None
      //case ((_, None), op) => None
      //case (_, (_:FixOp | _:FltOp)) =>
        //throw PIRException(s"Float Op to Boolean type op=$op ins=$ins")
      //case (ins, op) =>
        //throw PIRException(s"Don't know how to eval $op for ins=$ins")
    //}
  //}
}
