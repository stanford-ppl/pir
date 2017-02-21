package pir.graph

import pir.Design
import pir.graph._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

trait Variable extends Node
/* Register declared outside CU for communication between two CU. Only a symbol to keep track of
 * the scalar value, not a real register */
case class Scalar(name:Option[String])(implicit design: Design) extends Variable {
  override val typeStr = "Scalar"
  var writer:ScalarOut = _ 
  val readers:Set[ScalarIn] = Set[ScalarIn]() 
  def addReader(r:ScalarIn) = { readers += r; this }
  def setWriter(w:ScalarOut) = { 
    assert(writer == null, s"Already set ${this}'s writer to ${writer}, but trying to reset to ${w}")
    writer = w; this 
  }
  var dummyVector:DummyVector = _
  override def toUpdate = super.toUpdate || writer==null
}
object Scalar {
  def apply(name:String)(implicit design: Design):Scalar = Scalar(Some(name)) 
  def apply()(implicit design: Design):Scalar = Scalar(None) 
}

trait ArgIn extends Scalar{ override val typeStr = "ArgIn" }
object ArgIn {
  def apply() (implicit design: Design):Scalar = new Scalar(None) with ArgIn
  def apply(name:String) (implicit design: Design):Scalar = new Scalar(Some(name)) with ArgIn
}

trait ArgOut extends Scalar{ override val typeStr = "ArgOut" }
object ArgOut {
  def apply() (implicit design: Design):Scalar = new Scalar(None) with ArgOut
  def apply(name:String) (implicit design: Design):Scalar = new Scalar(Some(name)) with ArgOut
}

class Vector(val name:Option[String])(implicit design: Design) extends Variable {
  override val typeStr = "Vector"
  private var _writer:VecOut = _
  def writer:VecOut = _writer
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
  def apply(name:String)(implicit design: Design):Vector = new Vector(Some(name)) 
  def apply()(implicit design: Design):Vector = new Vector(None) 
}

class DummyVector(name:Option[String])(implicit design:Design) extends Vector(name) {
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

case class OffChip(name:Option[String])(implicit design: Design) extends Node{
  override val typeStr = "OffChip"
}
object OffChip {
  def apply()(implicit design:Design):OffChip = OffChip(None)
  def apply(name:String)(implicit design:Design):OffChip = OffChip(Some(name))
}

