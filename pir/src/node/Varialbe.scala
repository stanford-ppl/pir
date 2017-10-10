package pir.node

import pir._
import pirc._
import pirc.util._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

trait Variable extends Node {
  private val _readers:Set[GlobalInput] = Set[GlobalInput]() 
  def par:Int
  def readers:List[GlobalInput] = _readers.toList
  def addReader(r:GlobalInput) = { _readers += r; if (!writerIsEmpty) writer.connect(r); this }
  def removeReader(r:GlobalInput) = { _readers -= r; if (!writerIsEmpty) writer.disconnect(r) }

  private var _writer:GlobalOutput = _ 
  def writer:GlobalOutput = _writer
  def writerIsEmpty = writer == null
  def setWriter(w:GlobalOutput) = { 
    assert(_writer == null, s"Already set ${this}'s writer to ${_writer}, but trying to reset to ${w}")
    _writer = w
    readers.foreach { r => writer.connect(r) }
    this 
  }
}

class Control()(implicit design: PIR) extends Variable {
  def par:Int = 1 //TODO
}
object Control {
  def apply(name:String)(implicit design: PIR):Control = new Control().name(name) 
  def apply()(implicit design: PIR):Control = new Control() 
}
/* Register declared outside CU for communication between two CU. Only a symbol to keep track of
 * the scalar value, not a real register */
class Scalar()(implicit design: PIR) extends Variable {
  def par:Int = 1 //TODO
}
object Scalar {
  def apply(name:String)(implicit design: PIR):Scalar = new Scalar().name(name) 
  def apply()(implicit design: PIR):Scalar = new Scalar() 
}

case class DRAMAddress()(implicit design:PIR) extends ArgIn {
  override val typeStr = "DramAddress"
  private var _offchip:Either[String, OffChip] = _
  def setOffChip(offchip:String):Unit = { 
    _offchip = Left(offchip)
    design.updateLater(offchip, (n:Node) => setOffChip(n.asInstanceOf[OffChip]))
  }
  def setOffChip(offchip:OffChip):Unit = { _offchip = Right(offchip) }
}
object DRAMAddress {
  def apply(name:String, offchip:String)(implicit design:PIR):DRAMAddress = {
    val da = DRAMAddress().name(name)
    da.setOffChip(offchip)
    da
  }
}

class ArgIn(implicit design:PIR) extends Scalar
object ArgIn {
  def apply() (implicit design: PIR):Scalar = new ArgIn()
  def apply(name:String) (implicit design: PIR):Scalar = new ArgIn().name(name)
}

case class ArgOut()(implicit design: PIR) extends Scalar { override val typeStr = "ArgOut" }
object ArgOut {
  def apply(name:String) (implicit design: PIR):Scalar = new ArgOut().name(name) 
}

class Vector(implicit design: PIR) extends Variable {
  def par:Int = 16 //TODO
}
object Vector {
  def apply()(implicit design: PIR):Vector = new Vector() 
  def apply(name:String)(implicit design: PIR):Vector = new Vector().name(name)
}

case class OffChip()(implicit design: PIR) extends Node{
  override val typeStr = "OffChip"
}
object OffChip {
  def apply(name:String)(implicit design:PIR):OffChip = OffChip().name(name)
}

