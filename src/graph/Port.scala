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


trait Port extends Node {
  val src:Node
}
trait InPort extends Port {
  override val name=None
  override val typeStr = "InPort"
  var from:OutPort = _
  def isConnected = from!=null
  def connect(o:OutPort) = { 
    if (isConnected) assert(from == o, s"${this} is already connected but trying to reconnect to $o")
    from = o; o.to += this
  }
  def unconnect = { if (isConnected) from.to -= this; from = null }
  def isConnectedTo(o:OutPort) = { from == o }
}
object InPort {
  def apply[S<:Node](s:S)(implicit design:Design):InPort = new {override val src:S = s} with InPort
  def apply[S<:Node](s:S, toStr: => String)(implicit design:Design):InPort = {
    new {override val src:S = s} with InPort {override def toString = toStr}
  }
  def apply[S<:Node](s:S, f:OutPort, toStr: => String)(implicit design:Design):InPort = {
    val ip = InPort(s, toStr)
    ip.connect(f)
    ip
  }
}
/**
 * A type representing a group of wires in pir
 */
trait OutPort extends Port {
  val to:ListBuffer[InPort] = new ListBuffer[InPort]()
  def isConnected = to.size!=0
  def isConnectedTo(i:InPort) = { to.contains(i) }
  override val name=None
  override val typeStr = "OutPort"
  def width(implicit design:Design) = design.arch.wordWidth
  def by(step:OutPort)(implicit design:Design) = (Const("0i").out, this, step)
  def until(max:OutPort) = new Range(this, max)
}
object OutPort {
  def apply(s:Node)(implicit design:Design) = new {override val src = s} with OutPort
  def apply(s:Node, toStr: => String)(implicit design:Design) = {
    new {override val src = s} with OutPort { override def toString = toStr }
  }
  def apply(s:Node, t:InPort, toStr: => String)(implicit design:Design) = {
    new {override val src = s} with OutPort { override def toString = toStr; t.connect(this)}
  }
}

/* SRAM Ports */
trait RdAddrInPort extends InPort { override val src:SRAM }
object RdAddrInPort {
  def apply(s:SRAM, toStr: => String)(implicit design:Design):RdAddrInPort = {
    new {override val src = s} with RdAddrInPort {override def toString = toStr}
  }
}
trait WtAddrInPort extends InPort { override val src:SRAM }
object WtAddrInPort {
  def apply(s:SRAM, toStr: => String)(implicit design:Design):WtAddrInPort = {
    new {override val src = s} with WtAddrInPort {override def toString = toStr}
  }
}
trait WriteInPort extends InPort { override val src:SRAM }
object WriteInPort {
  def apply(s:SRAM, toStr: => String)(implicit design:Design):WriteInPort = {
    new {override val src = s} with WriteInPort {override def toString = toStr}
  }
}
trait ReadOutPort extends OutPort { override val src:SRAM }
object ReadOutPort {
  def apply(s:SRAM, toStr: => String)(implicit design:Design):ReadOutPort = {
    new {override val src = s} with ReadOutPort {override def toString = toStr}
  }
}
/* Inner Counter En Port */
trait EnInPort extends InPort { 
  override val src:Counter
}
object EnInPort {
  def apply(s:Counter, toStr: => String)(implicit design:Design):EnInPort = {
    new {override val src = s} with EnInPort {override def toString = toStr}
  }
}
/* Outer Counter Done Port */
trait DoneOutPort extends OutPort { 
  override val src:Counter
}
object DoneOutPort {
  def apply(s:Counter, toStr: => String)(implicit design:Design):DoneOutPort = {
    new {override val src = s} with DoneOutPort {override def toString = toStr}
  }
}

case class CtrlInPort(src:CtrlBox)(implicit design:Design) extends InPort { 
  override def toString = s"${src}.i[${indexOf(this)}]"
  val out = OutPort(this, s"${this}.op")
}
object CtrlInPort extends Metadata {
  def apply(src:CtrlBox, index:Int, in:InPort)(implicit design:Design):CtrlInPort = {
    val ci = CtrlInPort(src)
    indexOf(ci) = index
    in.connect(ci.out)
    ci
  }
}
case class CtrlOutPort(src:CtrlBox)(implicit design:Design) extends OutPort { 
  override def toString = s"${src}.o[${indexOf(this)}]"
  val in = InPort(this, s"${this}.ip")
}
object CtrlOutPort extends Metadata {
  def apply(src:CtrlBox, index:Int, out:OutPort)(implicit design:Design):CtrlOutPort = {
    val co = CtrlOutPort(src)
    indexOf(co) = index
    co.in.connect(out)
    co
  }
}
