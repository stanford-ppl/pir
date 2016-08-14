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
  def connect(o:OutPort) = {from = o; o.to += this}
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
  val to:ListBuffer[InPort] = new ListBuffer[InPort]()
  def isConnected = to.size!=0
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
/* PipeReg Ports */
trait PRInPort extends InPort { override val src:PipeReg }
object PRInPort {
  def apply(s:PipeReg, toStr: => String)(implicit design:Design):PRInPort = {
    new {override val src = s} with PRInPort {override def toString = toStr}
  }
}
trait PROutPort extends OutPort { override val src:PipeReg }
object PROutPort {
  def apply(s:PipeReg, toStr: => String)(implicit design:Design):PROutPort = {
    new {override val src = s} with PROutPort {override def toString = toStr}
  }
}
/* Ctr Ports */
trait CtrOutPort extends OutPort { override val src:Counter }
object CtrOutPort {
  def apply(s:Counter, toStr: => String)(implicit design:Design):CtrOutPort = {
    new {override val src = s} with CtrOutPort {override def toString = toStr}
  }
}
/* ScalarIn Port */
trait ScalarInOutPort extends OutPort { override val src:ScalarIn }
object ScalarInOutPort {
  def apply(s:ScalarIn, toStr: => String)(implicit design:Design):ScalarInOutPort = {
    new {override val src = s} with ScalarInOutPort {override def toString = toStr}
  }
}
/* ScalarOut Port */
trait ScalarOutInPort extends InPort { override val src:ScalarOut }
object ScalarOutInPort {
  def apply(s:ScalarOut, toStr: => String)(implicit design:Design):ScalarOutInPort = {
    new {override val src = s} with ScalarOutInPort {override def toString = toStr}
  }
}
/* VecIn Port*/
trait VecInOutPort extends OutPort { override val src:VecIn }
object VecInOutPort {
  def apply(s:VecIn, toStr: => String)(implicit design:Design):VecInOutPort = {
    new {override val src = s} with VecInOutPort {override def toString = toStr}
  }
}
/* VecOut Port*/
trait VecOutInPort extends InPort { override val src:VecOut }
object VecOutInPort {
  def apply(s:VecOut, toStr: => String)(implicit design:Design):VecOutInPort = {
    new {override val src = s} with VecOutInPort {override def toString = toStr}
  }
}
/* Const OutPort */
trait ConstOutPort extends OutPort { override val src:Const }
object ConstOutPort {
  def apply(s:Const, toStr: => String)(implicit design:Design):ConstOutPort = {
    new {override val src = s} with ConstOutPort {override def toString = toStr}
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
