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
    setPrev(pv.valid, nv.valid)
    pv.next = Some(nv)
    nv.prev = Some(pv)
    pv.set { pv => pv copy nv }
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
      assert(!sim.inSimulation, s"Adding previous value during simulation!")
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

  //def changed(implicit sim:Simulator):Boolean = v.value != pv.value

  def check(implicit sim:Simulator):Unit = v.check

  def update(implicit sim:Simulator):Unit = { 
    assert(sim.inSimulation || sim.inRegistration)
    if (v.isDefined) v.update
  }

  def clearUpdate(implicit sim:Simulator) = {
    values.foreach(_.clearUpdate)
  }

  def reset = {
    values.foreach(_.reset)
    _values.clear
  }

  def zero(implicit sim:Simulator) = {
    values.foreach(_.zero)
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

  var _funcHasRan = false
  def funcHasRan = func.isEmpty || _funcHasRan
  var func: Option[(this.type => Unit, String)] = None 
  def set(f: this.type => Unit)(implicit sim:Simulator):Unit = {
    if (next.isEmpty) assert(!sim.inSimulation)
    val stackTrace = getStackTrace(1, 20)
    func.foreach { func =>
      var info = s"Reseting func of value $this of io ${io} in ${if (io!=null) s"${io.src}" else "null"}\n"
      info += s"Redefinition at \n${getStackTrace(4, 20)}\n"
      info += s"Originally defined at \n${func._2}"
      warn(info)
    }
    func = Some((f,stackTrace)) 
  }
  def check(implicit sim:Simulator):Unit = {}
  def isDefined:Boolean
  def svalue(implicit sim:Simulator) = this match {
    case v:BusValue => v.value.map(ev => sim.quote(ev.value)) :+ sim.quote(v.valid.value)
    case v => sim.quote(v.value)
  }
  def updated:Boolean = funcHasRan && parent.fold(true) { p => !p.isDefined || p.funcHasRan }
  final def update(implicit sim:Simulator):this.type = {
    if (!isDefined) return this
    if (!updated) prevUpdate
    if (!updated) {
      sim.emitBlock(s"UpdateValue ${sim.quote(this)} #${sim.cycle} n${id} ${svalue}", {
        mainUpdate
      }, s"UpdateValue ${sim.quote(this)} #${sim.cycle} n${id} ${svalue}")
    }
    //postUpdate // allow cyclic update on previous value
    if (!updated) parent.foreach { parent => 
      parent.updateFunc
    }
    assert(updated, s"updated=false after update\n${updateInfo}")
    this
  }
  def prevUpdate(implicit sim:Simulator):Unit = { prev.foreach(_.update) }
  def mainUpdate(implicit sim:Simulator):Unit = {
    updateFunc
  }
  def updateFunc(implicit sim:Simulator):Unit = {
    if (!funcHasRan) {
      func.foreach { case (f, stackTrace) => 
        _funcHasRan = true
        try {
          f(this)
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
  }
  def postUpdate(implicit sim:Simulator):Unit = { next.foreach(_.update) }
  var prev:Option[Value] = None
  var next:Option[Value] = None
  def clearUpdate(implicit sim:Simulator):Unit = { _funcHasRan = false }
  def reset = {
    _funcHasRan = false
    func = None
    parent = None
    prev = None
    next = None
  }
  def zero(implicit sim:Simulator):Unit
  def updateInfo(implicit sim:Simulator):String = {
    var info = s"value=${sim.quote(this)} isDefined=${isDefined} updated=${updated}\n"
    info += s"func=${func.isDefined} funcHasRan=${funcHasRan}\n"
    info += s"next=${next.isDefined} nextIsDefined=${next.map(_.isDefined)}\n"
    info += s"parent=${parent.map { p => s"funcHasRan=${p.funcHasRan}"}}"
    info
  }
  def := (other:Value)(implicit sim:Simulator):Unit
  def <<= (other:Value)(implicit sim:Simulator):Unit = { 
    import sim.util._
    dprintln(s"${sim.quote(this)} <<= ${sim.quote(other)}")
    copy(other.update)
  }
  def copy (other:Value)(implicit sim:Simulator):Unit
}

trait SingleValue extends Value { self:PortType =>
  type E <: AnyVal
  type V = Option[E]
  var value:V = None
  def isVOrElse(x:Any)(matchFunc: V => Unit)(unmatchFunc: Any => Unit):Unit
  override def isDefined:Boolean = 
    (func.isDefined || parent.fold(false) { _.func.isDefined }) && 
    next.fold(true) { _.isDefined }
  def := (other:Value)(implicit sim:Simulator):Unit = {
    sim.dprintln(s"${sim.quote(this)} := ${sim.quote(other)}")
    set { _ <<= other }
  }
  def := (other: => Option[AnyVal])(implicit sim:Simulator):Unit = set { _ copy other }
  def <<= (other:Option[AnyVal])(implicit sim:Simulator):Unit = copy(other) 
  def copy(other:Value)(implicit sim:Simulator):Unit = copy(other.asInstanceOf[SingleValue].value)
  def copy (other:Option[AnyVal])(implicit sim:Simulator):Unit
  private var _default:Option[E] = None
  def default:Option[E] = _default
  def default_= (value:E)(implicit sim:Simulator) = {
    import sim.util._
    dprintln(s"${quote(this)}.default=$value")
    _default = Some(value)
  }
  def zero(implicit sim:Simulator):Unit = {
    import sim.util._
    value = default
  }
}

trait BitValue extends SingleValue { self:Bit =>
  type E = Boolean
  def isVOrElse(x:Any)(matchFunc: V => Unit)(unmatchFunc: Any => Unit) = x match {
    case v:Option[_] if v.fold(true) { _.isInstanceOf[E] } => matchFunc(v.asInstanceOf[V])
    case v => unmatchFunc(v)
  }
  def copy (other:Option[AnyVal])(implicit sim:Simulator) = {
    assert(sim.inSimulation || sim.inRegistration)
    value = other.asInstanceOf[V]
  }
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
  def & (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(BitAnd, this, vl)
  def | (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(BitOr, this, vl)
  def == (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(BitXnor, this, vl)
  def != (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(BitXor, this, vl)
  def not(implicit sim:Simulator):Option[AnyVal] = eval(BitNot, this)
}

trait WordValue extends SingleValue { self:Word =>
  type E = WordTp
  def isVOrElse(x:Any)(matchFunc: V => Unit)(unmatchFunc: Any => Unit) = x match {
    case v:Option[_] if v.fold(true) { _.isInstanceOf[E] } => matchFunc(v.asInstanceOf[V])
    case v => unmatchFunc(v)
  }
  def copy (other:Option[AnyVal])(implicit sim:Simulator):Unit = {
    assert(sim.inSimulation || sim.inRegistration)
    value = other.map {
      case v:Boolean if v => 1.0f
      case v:Boolean if !v => 0.0f
      case v => v.asInstanceOf[E]
    }
  }
  def <<= (other:Int)(implicit sim:Simulator):Unit = <<=(Some(other.toFloat)) 
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
  def / (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltDiv, this, vl)
  def >= (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltGeq, this, vl)
  def > (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltGt, this, vl)
  def < (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltLt, this, vl)
  def == (vl:Any)(implicit sim:Simulator):Option[AnyVal] = eval(FltEql, this, vl)
}

trait BusValue extends Value { self:Bus =>
  type V = List[Value] 
  val value:V = List.tabulate(busWidth) { i => 
    val eval = elemTp.clone(s"${elemTp.typeStr}v[$i]")
    eval.parent = Some(this)
    eval
  }
  lazy val valid = new Bit() { 
    this.io = self.io
    override def toString = s"${self.toString}.valid"
  }
  def s:String = value.map(_.s).mkString
  override def equals(that:Any):Boolean = {
    that match {
      case that: Bit => super.equals(that) && (this.value == that.value)
      case that => false
    }
  }
  def foreach(lambda:(Value, Int) => Unit):Unit =  {
    value.zipWithIndex.foreach { case (e, i) => lambda(e, i) }
  }
  def foreachv(lambda:(Value, Int) => Unit)(vlambda:BitValue => Unit):Unit =  {
    foreach(lambda)
    vlambda(valid)
  }
  def head:Value = value.head

  override def check(implicit sim:Simulator) = {
    import sim.util._
    if (func.isDefined && ((value :+ valid).exists(_.func.isDefined))) {
      warn(s"Bus ${quote(this)} has both group and individual update!")
    }
  }
  override def isDefined:Boolean = 
    (value.exists(_.isDefined) || func.isDefined || parent.fold(false) { _.func.isDefined }) && 
    next.fold(true) { _.isDefined } 
  override def updated = super.updated && 
                         value.forall(v => !v.isDefined || v.updated) && 
                         (!valid.isDefined || valid.updated)
  override def mainUpdate(implicit sim:Simulator):Unit = {
    import sim.util._
    super.mainUpdate
    value.foreach(_.update)
    valid.update
  }
  override def clearUpdate(implicit sim:Simulator) = {
    super.clearUpdate
    value.foreach(_.clearUpdate)
    valid.clearUpdate
  }
  override def updateInfo(implicit sim:Simulator):String = {
    var info = super.updateInfo
    info += s"${value.map{ v => s"${sim.quote(v)} updated=${v.updated}" }.mkString("\n")}\n"
    info += s"${sim.quote(valid)} updated=${valid.updated}"
    info
  }
  override def := (other:Value)(implicit sim:Simulator):Unit = {
    sim.dprintln(s"${sim.quote(this)} := ${sim.quote(other)}")
    other match {
      case other:SingleValue =>
        value.foreach { v => v := other }
      case other:BusValue =>
        (value, other.value).zipped.foreach { case (v, ov) => v := ov }
        valid := other.valid
    }
  }
  override def copy (other:Value)(implicit sim:Simulator) = {
    other match {
      case other:SingleValue =>
        value.foreach { v => v copy other }
      case other:BusValue =>
        (value, other.value).zipped.foreach { case (v, ov) => v copy ov }
        valid copy other.valid
    }
  }
  def zero(implicit sim:Simulator):Unit = {
    value.foreach(_.zero)
    valid.zero
  }
}

