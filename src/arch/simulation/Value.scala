package pir.plasticine.simulation

import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util.enums._
import pir.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.reflect.{ClassTag, classTag}

abstract class Val[V](implicit val tp:ClassTag[V]) {
  val io:IO[_<:PortType, Module]
  var value:V

  var func: Option[Simulator => V] = None 
  def set(f:Simulator => Any):Unit = {
    func = Some(f.asInstanceOf[Simulator => V])
  }
  def update(implicit sim:Simulator) = { 
    func.foreach { f => value = f(sim) }
    sim.updated += this
  }

  def isV(x:Val[_]) = x.tp==tp

  def + (v:Val[V]):V = eval(v, FixAdd)
  def - (v:Val[V]):V = eval(v, FixSub)

  def eval(v:Val[V], op:Op):V
  def eval(op:Op):V

  def toDecOp(op:Op):Any => Option[Float] = {
    def func(ins:Any):Option[Float] = {
      (ins, op) match {
        case ((a:Float, b:Float), FixAdd) => Some(a.toInt + b.toInt)
        case ((a:Float, b:Float), FixSub) => Some(a.toInt - b.toInt)
        case ((a:Float, b:Float), FltAdd) => Some(a.toFloat + b.toFloat)
        case ((a:Float, b:Float), FltSub) => Some(a.toFloat - b.toFloat)
        case (None, op) => None
        case ((None, _), op) => None
        case ((_, None), op) => None
        case (_, _:BitOp) =>
          throw PIRException(s"Boolean Op to Float type op=$op ins=$ins")
        case (ins, op) =>
          throw PIRException(s"Don't know how to eval $op for ins=$ins")
      }
    }
    func _
  }
  def toBoolOp(op:Op):Any => Option[Boolean] = {
    def func(ins:Any):Option[Boolean] = {
      (ins, op) match {
        case ((a:Boolean, b:Boolean), BitAnd) => Some(a && b)
        case ((a:Boolean, b:Boolean), BitOr) => Some(a || b)
        case (None, op) => None
        case ((None, _), op) => None
        case ((_, None), op) => None
        case (_, (_:FixOp | _:FltOp)) =>
          throw PIRException(s"Float Op to Boolean type op=$op ins=$ins")
        case (ins, op) =>
          throw PIRException(s"Don't know how to eval $op for ins=$ins")
      }
    }
    func _
  }

}

case class BusVal(io:IO[Bus, Module]) extends Val[Array[Option[Float]]] {
  val Bus(busWidth, _) = io.tp
  type V = Array[Option[Float]]
  var value:V = Array.fill(busWidth)(None)
  def eval(v:Val[V], op:Op):V = {
    (value, v.value).zipped.map{ case (a,b) => toDecOp(op)((a,b)) }
  }
  def eval(op:Op):V = { value.map{ x => toDecOp(op)(x) } }
}

case class WordVal(io:IO[Word, Module]) extends Val[Option[Float]] {
  type V = Option[Float]
  var value:V = None
  def eval(v:Val[V], op:Op):V = toDecOp(op)((value, v.value))
  def eval(op:Op):V = toDecOp(op)(value)
}

case class BitVal(io:IO[Bit, Module]) extends Val[Option[Boolean]] {
  type V = Option[Boolean]
  var value:V = None
  def eval(v:Val[V], op:Op):V = { toBoolOp(op)((value, v.value)) }
  def eval(op:Op):V = { toBoolOp(op)(value) }
}

