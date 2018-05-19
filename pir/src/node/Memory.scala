package pir
package node

abstract class Memory(implicit design:PIRDesign) extends Primitive 

case class SRAM(size:Int, banking:Banking)(implicit design:PIRDesign) extends Memory
case class RegFile(sizes:List[Int], inits:Option[List[AnyVal]])(implicit design:PIRDesign) extends Memory
case class LUT(inits:List[Any], banking:Banking)(implicit design:PIRDesign) extends Memory
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

case class DramAddress(dram:DRAM)(implicit design:PIRDesign) extends Memory

trait Stream extends Memory {
  val field:String
}
case class StreamIn(field:String)(implicit design:PIRDesign) extends Stream
case class StreamOut(field:String)(implicit design:PIRDesign) extends Stream

case class TokenOut()(implicit design:PIRDesign) extends Memory

case class RetimingFIFO()(implicit design:PIRDesign) extends Memory

trait PIRMemory {
  def isFIFO(n:PIRNode) = n match {
    case n:FIFO => true
    case n:RetimingFIFO => true
    case n:StreamIn => true
    case n:StreamOut => true
    case _ => false
  }

  def isReg(n:PIRNode) = n match {
    case n:Reg => true
    case n:ArgIn => true
    case n:DramAddress => true
    case n:ArgOut => true
    case n:TokenOut => true
    case n => false
  }

  def isRemoteMem(n:PIRNode) = n match {
    case (_:SRAM)  => true
    case n:FIFO if writersOf(n).size > 1 => true
    case n:RegFile => true
    case n:LUT => true
    case _ => false
  }

  def isControlMem(n:Memory) = n match {
    case n:TokenOut => true
    case StreamIn("ack") => true
    case _ => false
  }

}
