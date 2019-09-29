package pir
package node

import prism.graph._

trait Bus extends Serializable {
  def withLastBit = this match {
    case bus:FileEOFBus => true
    case _ => false
  }
}
case object DRAMBus extends Bus
class FileBus(val fileName:String) extends Bus
object FileBus {
  def apply(fileName:String) = new FileBus(fileName)
  def unapply(x:FileBus) = Some(x.fileName)
}
case class FileEOFBus(override val fileName:String) extends FileBus(fileName)
case class BlackBoxBus(name:String) extends Bus

trait BlackBox extends PIRNode

trait FringeCommand extends BlackBox

trait DRAMCommand extends FringeCommand {
  def dram:DRAM
  val data:NodeField[PIRNode]
}

trait DRAMDenseCommand extends DRAMCommand {
  val offset = new InputField[PIRNode]("offset")
  val size = new InputField[PIRNode]("size")
}
trait DRAMSparseCommand extends DRAMCommand {
  val addr = new InputField[PIRNode]("addr")
}
trait DRAMLoadCommand extends DRAMCommand {
  val data = new OutputField[PIRNode]("data")
}
trait DRAMStoreCommand extends DRAMCommand {
  val data = new InputField[PIRNode]("data")
  val ack = new OutputField[PIRNode]("ack")
}

case class FringeDenseLoad(dram:DRAM)(implicit env:Env) extends DRAMDenseCommand with DRAMLoadCommand

case class FringeSparseLoad(dram:DRAM)(implicit env:Env) extends DRAMSparseCommand with DRAMLoadCommand

case class FringeDenseStore(dram:DRAM)(implicit env:Env) extends DRAMDenseCommand with DRAMStoreCommand {
  val valid = new InputField[PIRNode]("valid")
}

case class FringeSparseStore(dram:DRAM)(implicit env:Env) extends DRAMSparseCommand with DRAMStoreCommand

case class DRAM(sid:String) extends prism.graph.IR {
  val dims = Metadata[List[Int]]("dims")
  val sname = new Metadata[String]("sname")
  val tp = new Metadata[BitType]("tp")
}

trait StreamCommand extends FringeCommand {
  def streams:List[EN[PIRNode] with Field[PIRNode]]
}

case class FringeStreamWrite(bus:Bus)(implicit env:Env) extends StreamCommand {
  def addStreams(xs:List[Any]) = DynamicOutputFields[PIRNode]("stream", xs)
  override def streams = getDynamicOutputFields[PIRNode]("stream")
}
case class FringeStreamRead(bus:Bus)(implicit env:Env) extends StreamCommand {
  def addStreams(xs:List[Any]) = DynamicInputFields[PIRNode]("stream", xs)
  override def streams = getDynamicInputFields[PIRNode]("stream")
  val lastBit = new OutputField[Option[PIRNode]]("lastBit")
}
