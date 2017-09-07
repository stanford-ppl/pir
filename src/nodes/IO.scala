package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir._
import pir.graph._

class Range (s:OutPort, e:OutPort) {
  val start:OutPort = s
  val end:OutPort = e
  def by(step:OutPort) = (start, end, step)
}

abstract class Port(implicit val src:Module, design:Design) extends Node {
  src.addIO(this)
  def isOutput = this.isInstanceOf[OutPort]
  def isInput = this.isInstanceOf[InPort]
  def asOutput = this.asInstanceOf[OutPort]
  def asInput = this.asInstanceOf[InPort]

  def ctrler:Controller = src match {
    case p:Primitive => p.ctrler
    case top:Top => top
    case mc:MemoryController => mc
  }

  def isGlobal:Boolean
}
class InPort(implicit src:Module, design:Design) extends Port {
  override val name=None
  override val typeStr = "InPort"
  var from:OutPort = _
  def isConnected = from!=null
  def connect(o:OutPort) = { 
    if (isConnected) assert(from == o, s"${this}(id=$id) is already connected to ${from} but trying to reconnect to $o")
    from = o; 
    if (!o.to.contains(this)) o.to += this
  }
  def isConnectedTo(o:OutPort) = { from == o }
  def disconnect = { if (isConnected) from.to -= this; from = null }

  def isGlobal:Boolean = { isConnected && !from.src.isConst && from.ctrler != ctrler }
}
object InPort {
  def apply[S<:Module](s:S)(implicit design:Design):InPort = new InPort()(s, design)
  def apply[S<:Module](s:S, toStr: => String)(implicit design:Design):InPort = {
    new InPort()(s, design) {override def toString = toStr}
  }
}
/**
 * A type representing a group of wires in pir
 */
class OutPort(implicit src:Module, design:Design) extends Port {
  val to:ListBuffer[InPort] = new ListBuffer[InPort]()
  def isConnected = to.size!=0
  def isConnectedTo(i:InPort) = { to.contains(i) }
  def disconnect = { to.foreach { _.disconnect}; assert(to.isEmpty) }
  override val name=None
  override val typeStr = "OutPort"
  def width(implicit design:Design) = design.arch.wordWidth
  def by(step:OutPort)(implicit design:Design) = (Const(0).out, this, step)
  def until(max:OutPort) = new Range(this, max)

  def isGlobal:Boolean = { isConnected && to.exists{ ip => !ip.src.isConst && ip.ctrler != ctrler } }
}
object OutPort {
  def apply(s:Module)(implicit design:Design):OutPort = new OutPort()(s, design)
  def apply(s:Module, toStr: => String)(implicit design:Design):OutPort = {
    new OutPort()(s, design) { override def toString = toStr }
  }
  def apply(s:Module, t:InPort, toStr: => String)(implicit design:Design):OutPort = {
    new OutPort()(s, design) { override def toString = toStr; t.connect(this)}
  }
}

