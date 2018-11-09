package spade
package node
import param._

abstract class OnChipMem(param:OnChipMemParam)(implicit design:SpadeDesign) extends Module {
  val dequeueEnable = Input[Bit](s"deqEn")
  val enqueueEnable = Input[Bit](s"deqEn")
  val notEmpty = Input[Bit](s"notEmpty")
  val notFull = Input[Bit](s"notFull")
  val counter = Module(BufferCounter(), "bufferCounter")

  counter.inc <== enqueueEnable.ic
  counter.dec <== dequeueEnable.ic
  notFull <== counter.notFull
  notEmpty <== counter.notEmpty
}

case class BufferCounter()(implicit sapde:SpadeDesign) extends Module {
  val inc = Input[Bit](s"inc")
  val dec = Input[Bit](s"dec")
  val count = Output[Word](s"count")
  val notFull = Output[Bit](s"notFull")
  val notEmpty = Output[Bit](s"notEmpty")
}

case class SRAM(param:SRAMParam)(implicit design:SpadeDesign) extends OnChipMem(param) {
  val writePort = Input[Vector](s"writePort")
  val writeAddr = Input[Vector](s"writeAddr")
  val readAddr = Input[Vector](s"readAddr")
  val readPort = Output[Vector](s"readPort")
}

case class FIFO[B<:PinType:ClassTag](param:FIFOParam)(implicit design:SpadeDesign) extends OnChipMem(param) {
  val bct = implicitly[ClassTag[B]]
  val writePort = Input[B](s"writePort")
  val readPort = Output[B](s"readPort")
}

//case class Reg()(implicit design:SpadeDesign) extends OnChipMem(RegParam) {
  //val writePort = Input[Word](s"writePort")
  //val readPort = Output[Word](s"readPort")
//}


