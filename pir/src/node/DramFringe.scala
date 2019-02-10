package pir
package node

trait FringeCommand extends PIRNode

trait DRAMCommand extends FringeCommand {
  def dram:DRAM
}

trait DRAMDenseCommand extends DRAMCommand {
  val offset = new InputField[PIRNode]("offset")
  val size = new InputField[PIRNode]("size")
  val deqCmd = new OutputField[List[PIRNode]]("deqCmd")
}
trait DRAMSparseCommand extends DRAMCommand {
  val addr = new InputField[PIRNode]("addr")
  val deqCmd = new OutputField[List[PIRNode]]("deqCmd")
}
trait DRAMLoadCommand extends DRAMCommand {
  val data = new OutputField[PIRNode]("data")
  val dataValid = new OutputField[List[PIRNode]]("dataValid")
}
trait DRAMStoreCommand extends DRAMCommand {
  val data = new InputField[PIRNode]("data")
  val ack = new OutputField[PIRNode]("ack")
  val deqData = new OutputField[List[PIRNode]]("deqData")
  val ackValid = new OutputField[List[PIRNode]]("ackValid")
}

case class FringeDenseLoad(dram:DRAM)(implicit env:Env) extends DRAMDenseCommand with DRAMLoadCommand

case class FringeSparseLoad(dram:DRAM)(implicit env:Env) extends DRAMSparseCommand with DRAMLoadCommand

case class FringeDenseStore(dram:DRAM)(implicit env:Env) extends DRAMDenseCommand with DRAMStoreCommand {
  val valid = new InputField[PIRNode]("valid")
}

case class FringeSparseStore(dram:DRAM)(implicit env:Env) extends DRAMSparseCommand with DRAMStoreCommand

case class DRAM(sid:String) extends prism.graph.IR {
  val dims = Metadata[List[Int]]("dims")
}

trait StreamCommand extends FringeCommand

case class FringeStreamWrite()(implicit env:Env) extends StreamCommand {
  val stream = new OutputField[PIRNode]("stream")
  val dataValid = new OutputField[List[PIRNode]]("dataValid")
}
case class FringeStreamRead()(implicit env:Env) extends StreamCommand {
  val stream = new InputField[PIRNode]("stream")
  val deqData = new OutputField[List[PIRNode]]("deqData")
}
