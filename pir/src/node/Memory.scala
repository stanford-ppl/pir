package pir.node

abstract class Memory(implicit design:PIRDesign) extends Primitive 

case class SRAM(size:Int, banking:Banking)(implicit design:PIRDesign) extends Memory
case class RegFile(sizes:List[Int], inits:Option[List[AnyVal]])(implicit design:PIRDesign) extends Memory
case class FIFO(size:Int)(implicit design:PIRDesign) extends Memory

case class Reg(init:Option[AnyVal])(implicit design:PIRDesign) extends Memory
object Reg {
  def apply(init:AnyVal)(implicit design:PIRDesign):Reg = Reg(Some(init))
  def apply()(implicit design:PIRDesign):Reg = Reg(None)
}

case class ArgIn(init:Option[AnyVal])(implicit design:PIRDesign) extends Memory
object ArgIn {
  def apply(init:AnyVal)(implicit design:PIRDesign):ArgIn = ArgIn(Some(init))
  def apply()(implicit design:PIRDesign):ArgIn = ArgIn(None)
}

case class ArgOut(init:Option[AnyVal])(implicit design:PIRDesign) extends Memory
object ArgOut {
  def apply(init:AnyVal)(implicit design:PIRDesign):ArgOut = ArgOut(Some(init))
  def apply()(implicit design:PIRDesign):ArgOut = ArgOut(None)
}

case class StreamIn(field:String)(implicit design:PIRDesign) extends Memory
case class StreamOut(field:String)(implicit design:PIRDesign) extends Memory

case class TokenOut()(implicit design:PIRDesign) extends Memory

case class RetimingFIFO()(implicit design:PIRDesign) extends Memory

