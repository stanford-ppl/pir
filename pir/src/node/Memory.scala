package pir.node

import pir._

import pirc._
import pirc.enums._
import pirc.util._

case class DRAM()(implicit design:PIR) extends IR

abstract class Memory(implicit design:PIR) extends Primitive { self =>
  def newIn(implicit design:PIR):Input = {
    ins.filterNot(_.isConnected).headOption.getOrElse(new Input)
  }

  def writers = deps.collect { case s: LocalStore => s }
  def readers = depeds.collect { case l: LocalLoad => l }
  def accesses = writers ++ readers
}

case class SRAM(size:Int, banking:Banking)(implicit design:PIR) extends Memory
case class RegFile(sizes:List[Int], inits:Option[List[AnyVal]])(implicit design:PIR) extends Memory
case class FIFO(size:Int)(implicit design:PIR) extends Memory

case class Reg(init:Option[AnyVal])(implicit design:PIR) extends Memory
object Reg {
  def apply(init:AnyVal)(implicit design:PIR):Reg = Reg(Some(init))
  def apply()(implicit design:PIR):Reg = Reg(None)
}

case class ArgIn(init:Option[AnyVal])(implicit design:PIR) extends Memory
object ArgIn {
  def apply(init:AnyVal)(implicit design:PIR):ArgIn = ArgIn(Some(init))
  def apply()(implicit design:PIR):ArgIn = ArgIn(None)
}

case class ArgOut(init:Option[AnyVal])(implicit design:PIR) extends Memory
object ArgOut {
  def apply(init:AnyVal)(implicit design:PIR):ArgOut = ArgOut(Some(init))
  def apply()(implicit design:PIR):ArgOut = ArgOut(None)
}

case class StreamIn(field:String)(implicit design:PIR) extends Memory
case class StreamOut(field:String)(implicit design:PIR) extends Memory

case class RetimingFIFO()(implicit design:PIR) extends Memory

