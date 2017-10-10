package pir.node

import pir._

import scala.collection.mutable.ListBuffer
import scala.math.max

class Range (s:OutPort, e:OutPort) {
  val start:OutPort = s
  val end:OutPort = e
  def by(step:OutPort) = (start, end, step)
}

abstract class Port(implicit val src:Module, design:PIR) extends Node {
  src.addIO(this)
  def isConnected:Boolean
  def isOutput = this.isInstanceOf[OutPort]
  def isInput = this.isInstanceOf[InPort]
  def asOutput = this.asInstanceOf[OutPort]
  def asInput = this.asInstanceOf[InPort]

  def ctrler:Controller = src match {
    case p:Primitive => p.ctrler
    case ctrler:Controller => ctrler
  }

  def isGlobal:Boolean
}
class InPort(implicit src:Module, design:PIR) extends Port {
  override val typeStr = "InPort"
  var _from:OutPort = _
  def from:OutPort = _from
  def isConnected = from!=null
  def connect(o:OutPort):Unit = { 
    if (isConnected) {
      assert(from == o, s"${this}(id=$id) is already connected to ${from} but trying to reconnect to $o")
    } else {
      _from = o; 
      o.connect(this)
    }
  }
  def isConnectedTo(o:OutPort) = { from == o }
  def disconnect:Unit = if (isConnected) { from.disconnect(this); _from = null }

  def isGlobal:Boolean = { isConnected && !from.src.isConst && from.ctrler != ctrler }
}
object InPort {
  def apply[S<:Module](s:S)(implicit design:PIR):InPort = new InPort()(s, design)
  def apply[S<:Module](s:S, toStr: => String)(implicit design:PIR):InPort = {
    new InPort()(s, design) {override def toString = toStr}
  }
}
class OutPort(implicit src:Module, design:PIR) extends Port {
  val _to:ListBuffer[InPort] = new ListBuffer[InPort]()
  def to:List[InPort] = _to.toList
  def isConnected = to.size!=0
  def isConnectedTo(i:InPort) = { to.contains(i) }
  def connect(i:InPort):Unit = {
    if (!_to.contains(i)) {
      _to += i;
      i.connect(this)
    }
  } 
  def disconnect:Unit = { to.foreach { _.disconnect }; assert(to.isEmpty) }
  def disconnect(in:InPort):Unit = { 
    if (isConnectedTo(in)) {
      in.disconnect
      _to -= in
    }
  } 
  override val typeStr = "OutPort"
  def width(implicit design:PIR) = design.arch.wordWidth
  def by(step:OutPort)(implicit design:PIR) = (Const(0).out, this, step)
  def until(max:OutPort) = new Range(this, max)

  def isGlobal:Boolean = { isConnected && to.exists{ ip => !ip.src.isConst && ip.ctrler != ctrler } }
}
object OutPort {
  def apply(s:Module)(implicit design:PIR):OutPort = new OutPort()(s, design)
  def apply(s:Module, toStr: => String)(implicit design:PIR):OutPort = {
    new OutPort()(s, design) { override def toString = toStr }
  }
  def apply(s:Module, t:InPort, toStr: => String)(implicit design:PIR):OutPort = {
    new OutPort()(s, design) { override def toString = toStr; t.connect(this)}
  }
}

trait GlobalIO extends Port with Primitive {
  val variable:Variable
  override def equals(that: Any) = that match {
    case io:GlobalIO => io.variable==variable && io.ctrler == ctrler && this.isInput == io.isInput
    case _ => super.equals(that)
  }

  override def asInput = this.asInstanceOf[GlobalInput]
  override def asOutput = this.asInstanceOf[GlobalOutput]
  def isControl = variable.isInstanceOf[Control]
  def isScalar = variable.isInstanceOf[Scalar]
  def isVector = variable.isInstanceOf[Vector]
}

case class GlobalInput(variable:Variable)(implicit ctrler:Controller, design:PIR) extends InPort with GlobalIO {
  variable.addReader(this)
  //def writer:Output[Variable] = variable.writer
  val out = OutPort(this, s"${this}.out")
  override def from:GlobalOutput = super.from.asInstanceOf[GlobalOutput]
}

case class GlobalOutput(variable:Variable)(implicit ctrler:Controller, design:PIR) extends OutPort with GlobalIO {
  variable.setWriter(this)
  //def readers:List[Input[Variable]]
  val in = InPort(this, s"${this}.in")
  override def to:List[GlobalInput] = super.to.map{_.asInstanceOf[GlobalInput]}
}


