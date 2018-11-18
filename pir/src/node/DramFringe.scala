package pir
package node

abstract class DRAMCommand(implicit env:Env) extends PIRNode {
  def dram:DRAM
  val respondValid = new OutputField[List[BufferRead]]("respondValid")
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
}
