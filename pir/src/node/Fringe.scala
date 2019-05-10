package pir
package node

trait Bus
case object DRAMBus extends Bus
case class FileBus(fileName:String) extends Bus
case class BlackBoxBus(name:String) extends Bus

trait FringeCommand extends PIRNode

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

trait StreamCommand extends FringeCommand

case class FringeStreamWrite(bus:Bus)(implicit env:Env) extends StreamCommand {
  def addStreams(xs:List[Any]) = DynamicOutputFields[PIRNode]("stream", xs)
  def streams = getDynamicOutputFields[PIRNode]("stream")
}
case class FringeStreamRead(bus:Bus)(implicit env:Env) extends StreamCommand {
  def addStreams(xs:List[Any]) = DynamicInputFields[PIRNode]("stream", xs)
  def streams = getDynamicInputFields[PIRNode]("stream")
}
