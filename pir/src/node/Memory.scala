package pir.node

import pir._

import prism._
import prism.enums._
import prism.util._

case class DRAM()(implicit design:Design) extends IR

abstract class Memory(implicit design:Design) extends Primitive { self =>
  def writers = deps.collect { case s: LocalStore => s }
  def readers = depeds.collect { case l: LocalLoad => l }
  def accesses = writers ++ readers
}

case class SRAM(size:Int, banking:Banking)(implicit design:Design) extends Memory
case class RegFile(sizes:List[Int], inits:Option[List[AnyVal]])(implicit design:Design) extends Memory
case class FIFO(size:Int)(implicit design:Design) extends Memory

case class Reg(init:Option[AnyVal])(implicit design:Design) extends Memory
object Reg {
  def apply(init:AnyVal)(implicit design:Design):Reg = Reg(Some(init))
  def apply()(implicit design:Design):Reg = Reg(None)
}

case class ArgIn(init:Option[AnyVal])(implicit design:Design) extends Memory
object ArgIn {
  def apply(init:AnyVal)(implicit design:Design):ArgIn = ArgIn(Some(init))
  def apply()(implicit design:Design):ArgIn = ArgIn(None)
}

case class ArgOut(init:Option[AnyVal])(implicit design:Design) extends Memory
object ArgOut {
  def apply(init:AnyVal)(implicit design:Design):ArgOut = ArgOut(Some(init))
  def apply()(implicit design:Design):ArgOut = ArgOut(None)
}

case class StreamIn(field:String)(implicit design:Design) extends Memory
case class StreamOut(field:String)(implicit design:Design) extends Memory

case class RetimingFIFO()(implicit design:Design) extends Memory

