package pir.graph

import pir.Design
import pir.graph._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

/* Register declared outside CU for communication between two CU. Only a symbol to keep track of
 * the scalar value, not a real register */
case class Scalar(name:Option[String])(implicit design: Design) extends Node {
  override val typeStr = "Scalar"
  var writer:ScalarOut = _ 
  val readers:Set[ScalarIn] = Set[ScalarIn]() 
  def addReader(r:ScalarIn) = { readers += r; this }
  def setWriter(w:ScalarOut) = { 
    assert(writer == null, s"Already set ${this}'s writer to ${writer}, but trying to reset to ${w}")
    writer = w; this 
  }
  override def toUpdate = super.toUpdate || writer==null
}
object Scalar {
  def apply(name:String)(implicit design: Design):Scalar = Scalar(Some(name)) 
  def apply()(implicit design: Design):Scalar = Scalar(None) 
}

trait ArgIn extends Scalar{ override val typeStr = "ArgIn" }
object ArgIn {
  def apply() (implicit design: Design):Scalar = new Scalar(None) with ArgIn
  def apply(name:String) (implicit design: Design):Scalar = new Scalar(Some(name)) with ArgOut
}

trait ArgOut extends Scalar{ override val typeStr = "ArgOut" }
object ArgOut {
  def apply() (implicit design: Design):Scalar = new Scalar(None) with ArgOut
  def apply(name:String) (implicit design: Design):Scalar = new Scalar(Some(name)) with ArgOut
}

case class Vector(val name:Option[String])(implicit design: Design) extends Node {
  override val typeStr = "Vector"
  var writer:VecOut = _
  val readers:Set[VecIn] = Set[VecIn]() 
  def addReader(r:VecIn) = { readers += r; this }
  def setWriter(w:VecOut) = {
    assert(writer == null, s"Already set ${this}'s writer to ${writer}, but trying to reset to ${w}")
    writer = w; this
  }
  override def toUpdate = super.toUpdate || writer==null
}
object Vector {
  def apply(name:String)(implicit design: Design):Vector = Vector(Some(name)) 
  def apply()(implicit design: Design):Vector = Vector(None) 
}

case class OffChip(name:Option[String])(implicit design: Design) extends Node{
  override val typeStr = "OffChip"
}
object OffChip {
  def apply()(implicit design:Design):OffChip = OffChip(None)
  def apply(name:String)(implicit design:Design):OffChip = OffChip(Some(name))
}

