package pir.node

import pir.PIR
import pir.node._
import pir.exceptions._
import pir.util.misc._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

trait Variable extends Node {
  def writer:Output
  def readers:List[Input]
}
/* Register declared outside CU for communication between two CU. Only a symbol to keep track of
 * the scalar value, not a real register */
class Scalar(val name:Option[String])(implicit design: PIR) extends Variable {
  override val typeStr = "Scalar"
  var _writer:ScalarOut = _ 
  def writerIsEmpty = _writer == null
  def writer:ScalarOut = {
    if (_writer==null)
      warn(s"$this has no writer")
    _writer
  }
  private val _readers:Set[ScalarIn] = Set[ScalarIn]() 
  def readers:List[ScalarIn] = _readers.toList
  def removeReader(r:ScalarIn) = _readers -= r
  def addReader(r:ScalarIn) = { _readers += r; this }
  def setWriter(w:ScalarOut) = { 
    assert(_writer == null, s"Already set ${this}'s writer to ${_writer}, but trying to reset to ${w}")
    _writer = w; this 
  }
  var dummyVector:DummyVector = _
  override def toUpdate = super.toUpdate || writer==null
}
object Scalar {
  def apply(name:String)(implicit design: PIR):Scalar = new Scalar(Some(name)) 
  def apply()(implicit design: PIR):Scalar = new Scalar(None) 
}

case class DRAMAddress(override val name:Option[String])(implicit design:PIR) extends Scalar(name) with ArgIn {
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
    val da = DRAMAddress(Some(name))
    da.setOffChip(offchip)
    da
  }
}

trait ArgIn extends Scalar { 
  override val typeStr = "ArgIn"
}
object ArgIn {
  def apply() (implicit design: PIR):Scalar = new Scalar(None) with ArgIn
  def apply(name:String) (implicit design: PIR):Scalar = new Scalar(Some(name)) with ArgIn
}

trait ArgOut extends Scalar{ override val typeStr = "ArgOut" }
object ArgOut {
  def apply() (implicit design: PIR):Scalar = new Scalar(None) with ArgOut
  def apply(name:String) (implicit design: PIR):Scalar = new Scalar(Some(name)) with ArgOut
}

class Vector(val name:Option[String])(implicit design: PIR) extends Variable {
  override val typeStr = "Vector"
  private var _writer:VecOut = _
  def writer:VecOut = {
    if (_writer==null)
      warn(s"$this has no writer")
    _writer
  }
  private val _readers:Set[VecIn] = Set.empty
  def readers:List[VecIn] = _readers.toList
  def addReader(r:VecIn) = { _readers += r; this }
  def setWriter(w:VecOut) = {
    assert(_writer == null, s"Already set ${this}'s writer to ${_writer}, but trying to reset to ${w}")
    _writer = w; this
  }
  override def toUpdate = super.toUpdate || writer==null
}
object Vector {
  def apply(name:String)(implicit design: PIR):Vector = new Vector(Some(name)) 
  def apply()(implicit design: PIR):Vector = new Vector(None) 
}

class DummyVector(name:Option[String])(implicit design:PIR) extends Vector(name) {
  override val typeStr = "DVector"
  override def writer:DummyVecOut = super.writer.asInstanceOf[DummyVecOut]
  override def readers:List[DummyVecIn] = super.readers.asInstanceOf[List[DummyVecIn]]
  val scalars = Set[Scalar]()
  def isFull = scalars.size==design.arch.numLanes
  def remainSpace = design.arch.numLanes - scalars.size
  def addScalar(s:Scalar) = { 
    assert(scalars.size < design.arch.numLanes) 
    scalars += s
  }
  override def setWriter(w:VecOut) = {
    val writerCtrlers = scalars.map(_.writer.ctrler).toSet
    assert(writerCtrlers.size==1, 
      s"bundled scalars have more than 1 writers scalars:${scalars} writers: ${writerCtrlers}")
    assert(w.ctrler==writerCtrlers.head)
    super.setWriter(w)
  }
}

case class OffChip(name:Option[String])(implicit design: PIR) extends Node{
  override val typeStr = "OffChip"
}
object OffChip {
  def apply()(implicit design:PIR):OffChip = OffChip(None)
  def apply(name:String)(implicit design:PIR):OffChip = OffChip(Some(name))
}

