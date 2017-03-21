package pir.plasticine.simulation

import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util.enums._
import pir.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.reflect.{ClassTag, classTag}

trait Val[V] {
  implicit val ev:ClassTag[V] = classTag[V]
  val io:IO[_<:PortType, Module]
  var value:Option[V]

  var func: Option[Simulator => Option[V]] = None 
  def set(f:Simulator => Option[Any]):Unit = {
    func = Some((() => f).asInstanceOf[Simulator => Option[V]])
  }
  def update(implicit sim:Simulator) = { 
    func.foreach { f => value = f(sim) }
    sim.updated += this
  }

  def + (v:Val[V]):Option[V] = eval(v, FixAdd)
  def - (v:Val[V]):Option[V] = eval(v, FixSub)

  def eval(v:Val[V], op:Op):Option[V]
  def eval(op:Op):Option[V]

  def toDecOp(op:Op):Any => Float = {
    def func(ins:Any):Float = {
      (ins, op) match {
        case ((a:Float, b:Float), FixAdd) => a.toInt + b.toInt
        case ((a:Float, b:Float), FixSub) => a.toInt - b.toInt
        case ((a:Float, b:Float), FltAdd) => a.toFloat + b.toFloat
        case ((a:Float, b:Float), FltSub) => a.toFloat - b.toFloat
        case (_, _:BitOp) =>
          throw PIRException(s"Boolean Op to Float type op=$op ins=$ins")
        case (ins, op) =>
          throw PIRException(s"Don't know how to eval $op for ins=$ins")
      }
    }
    func _
  }
  def toBoolOp(op:Op):Any => Boolean = {
    def func(ins:Any):Boolean = {
      (ins, op) match {
        case ((a:Boolean, b:Boolean), BitAnd) => a && b 
        case ((a:Boolean, b:Boolean), BitOr) => a || b 
        case (_, (_:FixOp | _:FltOp)) =>
          throw PIRException(s"Float Op to Boolean type op=$op ins=$ins")
        case (ins, op) =>
          throw PIRException(s"Don't know how to eval $op for ins=$ins")
      }
    }
    func _
  }

}

case class BusVal(io:IO[Bus, Module]) extends Val[Array[Float]] {
  type V = Array[Float]
  var value:Option[V] = None
  def eval(v:Val[V], op:Op):Option[V] = {
    val res = value.zip(v.value).map{ case (a,b) => (a,b).zipped.map{ case (ax,bx) => toDecOp(op)((ax,bx)) } }
    if (res.isEmpty) None
    else Some(res.head)
  }
  def eval(op:Op):Option[V] = {
    val res = value.map{ _.map{ x =>  toDecOp(op)(x) } }
    if (res.isEmpty) None
    else Some(res.head)
  }
}

case class WordVal(io:IO[Word, Module]) extends Val[Float] {
  type V = Float
  var value:Option[V] = None
  def eval(v:Val[V], op:Op):Option[V] = {
    val res = value.zip(v.value).map{ case (a,b) => toDecOp(op)((a,b)) }
    if (res.isEmpty) None
    else Some(res.head)
  }
  def eval(op:Op):Option[V] = {
    val res = value.map{ x => toDecOp(op)(x) }
    if (res.isEmpty) None
    else Some(res.head)
  }
}

case class BitVal(io:IO[Bit, Module]) extends Val[Boolean] {
  type V = Boolean 
  var value:Option[V] = None
  def eval(v:Val[V], op:Op):Option[V] = {
    val res = value.zip(v.value).map{ case (a,b) => toBoolOp(op)((a,b)) }
    if (res.isEmpty) None
    else Some(res.head)
  }
  def eval(op:Op):Option[V] = {
    val res = value.map{ x => toBoolOp(op)(x) }
    if (res.isEmpty) None
    else Some(res.head)
  }
}

