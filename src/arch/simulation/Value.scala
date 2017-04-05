package pir.plasticine.simulation

import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util.enums._
import pir.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.reflect.{ClassTag, classTag}
import scala.reflect.runtime.universe._

abstract class Val[V](implicit val tp:ClassTag[V]) {
  val io:IO[_<:PortType, Module]
  var value:V
  var prevValue:V = _
  def changed:Boolean

  var func: Option[Simulator => V] = None 
  def set(f:Simulator => Any):Unit = { func = Some(f.asInstanceOf[Simulator => V]) }
  def update(implicit sim:Simulator):Unit = { 
    if (updated) return
    prevValue = value
    func.foreach { f => value = f(sim) }
    sim.updated += this
  }
  def updated(implicit sim:Simulator) = {
    sim.updated.contains(this)
  }

  def vs:String = s"$value"
  def pvs:String = s"$prevValue"

  def isV(x:Val[_]) = x.tp==tp
  def asV(x:Val[_]) = x.asInstanceOf[Val[V]] 

  def + (v:Val[_]):V = eval(asV(v), FixAdd)
  def - (v:Val[_]):V = eval(asV(v), FixSub)

  def eval(v:Val[V], op:Op):V
  def eval(op:Op):V

  def toDecOp(op:Op)(ins:Any):Option[Float] = {
    (ins, op) match {
      case ((Some(a:Float), Some(b:Float)), FixAdd) => Some(a.toInt + b.toInt)
      case ((Some(a:Float), Some(b:Float)), FixSub) => Some(a.toInt - b.toInt)
      case ((Some(a:Float), Some(b:Float)), FltAdd) => Some(a.toFloat + b.toFloat)
      case ((Some(a:Float), Some(b:Float)), FltSub) => Some(a.toFloat - b.toFloat)
      case (None, op) => None
      case ((None, _), op) => None
      case ((_, None), op) => None
      case (_, _:BitOp) =>
        throw PIRException(s"Boolean Op to Float type op=$op ins=$ins")
      case (ins, op) =>
        throw PIRException(s"Don't know how to eval $op for ins=$ins")
    }
  }
  def toBoolOp(op:Op)(ins:Any):Option[Boolean] = {
    (ins, op) match {
      case ((Some(a:Boolean), Some(b:Boolean)), BitAnd) => Some(a && b)
      case ((Some(a:Boolean), Some(b:Boolean)), BitOr) => Some(a || b)
      case (None, op) => None
      case ((None, _), op) => None
      case ((_, None), op) => None
      case (_, (_:FixOp | _:FltOp)) =>
        throw PIRException(s"Float Op to Boolean type op=$op ins=$ins")
      case (ins, op) =>
        throw PIRException(s"Don't know how to eval $op for ins=$ins")
    }
  }
}

case class BusVal[W](io:IO[Bus, Module], busWidth:Int)(implicit ev:TypeTag[W]) extends Val[Array[Option[W]]] {
  type V = Array[Option[W]]
  var value:V = Array.fill(busWidth)(None)
  def changed:Boolean = {
    if (prevValue==null) return true
    (prevValue, value).zipped.foreach{ case (p, v) => if (p!=v) return true }
    return false
  }
  def eval(v:Val[V], op:Op):V = {
    if (typeOf[W] =:= typeOf[Boolean]) {
      (value, v.value).zipped.map { case (a,b) => toBoolOp(op)(a,b).asInstanceOf[Option[W]] }
    } else if (typeOf[W] =:= typeOf[Float]) {
      (value, v.value).zipped.map { case (a,b) => toDecOp(op)(a,b).asInstanceOf[Option[W]] }
    } else {
      throw PIRException(s"Don't know how to evalue bus with type ${typeOf[W]}")
    }
  }
  def eval(op:Op):V = { 
    if (typeOf[W] =:= typeOf[Boolean]) {
      value.map{ x => toBoolOp(op)(x).asInstanceOf[Option[W]] }
    } else if (typeOf[W] =:= typeOf[Float]) {
      value.map{ x => toDecOp(op)(x).asInstanceOf[Option[W]] }
    } else {
      throw PIRException(s"Don't know how to evalue bus with type ${typeOf[W]}")
    }
  }
  def mkstr(array:Any) = {
    if (array==null) "null"
    else array.asInstanceOf[Array[_]].mkString(",")
  }
  override def vs:String = mkstr(value)
  override def pvs:String = mkstr(prevValue)
}
object BusVal {
  def apply(io:IO[Bus, Module]):BusVal[_] = {
    val Bus(busWidth, elemTp) = io.tp
    elemTp match {
      case _:Word => BusVal[Float](io, busWidth)
      case _:Bit => BusVal[Boolean](io, busWidth)
    }
  }
}

case class WordVal(io:IO[Word, Module]) extends Val[Option[Float]] {
  type V = Option[Float]
  var value:V = None
  def eval(v:Val[V], op:Op):V = toDecOp(op)((value, v.value))
  def eval(op:Op):V = toDecOp(op)(value)
  def changed = prevValue != value
}

case class BitVal(io:IO[Bit, Module]) extends Val[Option[Boolean]] {
  type V = Option[Boolean]
  var value:V = None
  def eval(v:Val[V], op:Op):V = { toBoolOp(op)((value, v.value)) }
  def eval(op:Op):V = { toBoolOp(op)(value) }
  def changed = prevValue != value
}

