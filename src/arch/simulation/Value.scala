package pir.plasticine.simulation

import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util._
import pir.util.enums._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.reflect.{ClassTag, classTag}

trait Val[V, P<:LinkType] {
  implicit val ev:ClassTag[V] = classTag[V]
  val io:IO[P, Module]
  var value:Option[V]

  var func: Option[Simulator => Option[V]] = None 
  def set(f:Simulator => Option[Any]):Unit = {
    func = Some((() => f).asInstanceOf[Simulator => Option[V]])
  }
  def update(implicit sim:Simulator) = { 
    func.foreach { f => value = f(sim) }
    sim.updated += this
  }

  def + (v:Val[V, P]):Option[V] = eval(v, FixAdd)
  def - (v:Val[V, P]):Option[V] = eval(v, FixSub)

  def eval(v:Val[V, P], op:Op):Option[V]
  def eval(op:Op):Option[V]

  def toDecOp(op:Op):Any => BigDecimal = {
    def func(ins:Any):BigDecimal = {
      (ins, op) match {
        case ((a:BigDecimal, b:BigDecimal), FixAdd) => a.toInt + b.toInt
        case ((a:BigDecimal, b:BigDecimal), FixSub) => a.toInt - b.toInt
        case ((a:BigDecimal, b:BigDecimal), FltAdd) => a.toFloat + b.toFloat
        case ((a:BigDecimal, b:BigDecimal), FltSub) => a.toFloat - b.toFloat
        case (_, _:BitOp) =>
          throw PIRException(s"Boolean Op to BigDecimal type op=$op ins=$ins")
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
          throw PIRException(s"BigDecimal Op to Boolean type op=$op ins=$ins")
        case (ins, op) =>
          throw PIRException(s"Don't know how to eval $op for ins=$ins")
      }
    }
    func _
  }

}

case class BusVal(io:IO[Bus, Module]) extends Val[Array[BigDecimal], Bus] {
  type V = Array[BigDecimal]
  type P = Bus 
  var value:Option[V] = None
  def eval(v:Val[V, P], op:Op):Option[V] = {
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

case class PortVal(io:IO[Port, Module]) extends Val[BigDecimal, Port] {
  type V = BigDecimal 
  type P = Port
  var value:Option[V] = None
  def eval(v:Val[V, P], op:Op):Option[V] = {
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

case class WireVal(io:IO[Wire, Module]) extends Val[Boolean, Wire] {
  type V = Boolean 
  type P = Wire
  var value:Option[V] = None
  def eval(v:Val[V, P], op:Op):Option[V] = {
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

