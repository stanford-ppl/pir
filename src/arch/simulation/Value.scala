package pir.plasticine.simulation

import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util.enums._
import pir.exceptions._
import pir.util.misc._
import pir.Config

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.reflect.{ClassTag, classTag}
import scala.reflect.runtime.universe._

case class Val[P<:PortType](io:IO[P, Module]) {
  override def toString:String = s"$io.v"

  val value:P = io.tp.clonetp
  val prevValue:P = io.tp.clonetp
  def changed:Boolean = value != prevValue

  var func: Option[this.type => Unit] = None 
  def set(f: this.type => Unit):Unit = {
    if (func!=None) warn(s"Reseting func for ${io} of ${io.src} ${getStackTrace(4, 6)}")
    func = Some(f) 
  }
  var _updated = false
  def updated = _updated
  def clearUpdate(implicit sim:Simulator) = {
    if (_updated!=true) throw PIRException(s"${io} is not updated at #${sim.cycle}")
    _updated = false 
  }
  def update(implicit sim:Simulator):Unit = { 
    if (updated) return
    prevValue.copy(value)
    func.foreach { f => f(this) }
    //sim.dprintln(Config.debug && func.nonEmpty, s"#${sim.cycle} updating ${this} val:${value.value}")
    _updated = true
  }
  def eval(implicit sim:Simulator):this.type = {
    update;
    this
  }

  def vs:String = s"${value.s}"
  def pvs:String = s"${prevValue.s}"

  def := (o: => Val[_<:PortType])(implicit sim:Simulator):Unit = {
    set{ v => v.value.copy(o.value) }
  }

  def <= (o: => IO[_<:PortType, Module])(implicit sim:Simulator):Unit = {
    := (o.ev)
  }

  def :== (o: => Val[_<:PortType])(implicit sim:Simulator):Unit = {
    set{ v => v.value.copy(o.prevValue) }
  }

  def <== (o: => IO[_<:PortType, Module])(implicit sim:Simulator):Unit = {
    :== (o.ev)
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
