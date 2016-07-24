package pir.graph

import pir.Design
import pir.graph._

/* Register declared outside CU for communication between two CU. Only a symbol to keep track of
 * the scalar value, not a real register */
case class Scalar(val name:Option[String])(implicit design: Design) extends Node {
  override val typeStr = "Scalar"
  //var writer:Controller = _ //TODO: need to keep track of these
  //val reader:Set[Controller] = Set[Controller]() 
  //toUpdate = false //TODO 
}
object Scalar {
  def apply(name:String)(implicit design: Design):Scalar = Scalar(Some(name)) 
  def apply()(implicit design: Design):Scalar = Scalar(None) 
}

trait ArgIn extends Scalar {
  override val typeStr = "ArgIn"
  toUpdate = false
}
object ArgIn {
  def apply(n:Option[String])(implicit design: Design):ArgIn = new Scalar(n) with ArgIn
  def apply() (implicit design: Design):ArgIn = ArgIn(None)
  def apply(name:String) (implicit design: Design):ArgIn = ArgIn(Some(name))
}

/** Scalar values passed from accelerator to host CPU code via memory-mapped registers.
 *  Represent scalar outputs from the accelerator, and are write-only from accelerator.
 */
trait ArgOut extends Scalar {
  override val typeStr = "ArgOut"
  toUpdate = false
} 
object ArgOut {
  def apply(n:Option[String])(implicit design: Design) = new Scalar(n) with ArgOut
  def apply() (implicit design: Design):ArgOut = ArgOut(None)
  def apply(name:String) (implicit design: Design):ArgOut = ArgOut(Some(name))
}

case class OffChip(name: Option[String])(implicit design: Design)
  extends Node {
  val typeStr = "OffChip"
}
object OffChip {
  def apply()(implicit design: Design): OffChip 
    = OffChip(None)
  def apply(name:String)(implicit design: Design): OffChip 
    = OffChip(Some(name))
}

