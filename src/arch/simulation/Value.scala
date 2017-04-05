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

case class Val[P<:PortType](io:IO[P, Module]) {
  val value:P = io.tp.clonetp
  val prevValue:P = io.tp.clonetp
  def changed:Boolean = value != prevValue

  var func: Option[P => Unit] = None 
  def set(f: P => Unit):Unit = func = Some(f) 
  var _updated = false
  def updated = _updated
  def clearUpdate = _updated = false 
  def update:Unit = { 
    if (updated) return
    prevValue.copy(value)
    func.foreach { f => f(value) }
    _updated = true
  }

  def vs:String = s"${value.s}"
  def pvs:String = s"${prevValue.s}"

  def <= (o:Val[_<:PortType]) = {
    set{ v => v.copy(o.value) }
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
