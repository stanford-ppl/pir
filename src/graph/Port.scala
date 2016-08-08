package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir.Design
import pir.graph._

class Range (s:OutPort, e:OutPort) {
  val start:OutPort = s
  val end:OutPort = e
  def by(step:OutPort) = (start, end, step)
}


trait Port extends Node {
  val src:Node
}
trait InPort extends Port{
  override val name=None
  override val typeStr = "InPort"
  var from:OutPort = _
  def isConnected = from!=null
  def connect(o:OutPort) = {from = o; o.to = this}
}
object InPort {
  def apply(s:Node)(implicit design:Design):InPort = new {override val src = s} with InPort
  def apply(s:Node, toStr: => String)(implicit design:Design):InPort = {
    new {override val src = s} with InPort {override def toString = toStr}
  }
  def apply(s:Node, f:OutPort, toStr: => String)(implicit design:Design):InPort = {
    new {override val src = s} with InPort {override def toString = toStr; connect(f) }
  }
}
/**
 * A type representing a group of wires in pir
 */
trait OutPort extends Port{
  var to:InPort = _
  def isConnected = to!=null
  override val name=None
  override val typeStr = "OutPort"
  def width(implicit design:Design) = design.arch.wordWidth
  def by(step:OutPort)(implicit design:Design) = (Const(0l).out, this, step)
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
trait PRInPort extends InPort { override val src:PipeReg }
object PRInPort {
  def apply(s:PipeReg, toStr: => String)(implicit design:Design):PRInPort = {
    new {override val src = s} with PRInPort {override def toString = toStr}
  }
}
trait CtrOutPort extends OutPort { override val src:Counter }
object CtrOutPort {
  def apply(s:Counter, toStr: => String)(implicit design:Design):CtrOutPort = {
    new {override val src = s} with CtrOutPort {override def toString = toStr}
  }
}
