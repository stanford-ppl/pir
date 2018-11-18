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

//case class CountAck(ack:Def)(implicit env:Env) extends ControlNode

//trait ProcessDramCommand extends Def
//case class ProcessDramDenseLoad(dram:DRAM, offset:LocalLoad, size:LocalLoad)(implicit env:Env) extends ProcessDramCommand
//case class ProcessDramDenseStore(dram:DRAM, offset:LocalLoad, size:LocalLoad, data:LocalLoad)(implicit env:Env) extends ProcessDramCommand
//case class ProcessDramSparseLoad(dram:DRAM, addr:LocalLoad)(implicit env:Env) extends ProcessDramCommand
//case class ProcessDramSparseStore(dram:DRAM, addr:LocalLoad, data:LocalLoad)(implicit env:Env) extends ProcessDramCommand

//case class DramControllerDone(en:ControlNode)(implicit env:Env) extends ControlNode

//trait DramFringeUtil {
  //def isLoadFringe(n:GlobalContainer) = n match {
    //case n:FringeDenseLoad => true
    //case n:FringeSparseLoad => true
    //case n => false
  //}

  //def isStoreFringe(n:GlobalContainer) = n match {
    //case n:FringeDenseStore => true
    //case n:FringeSparseStore => true
    //case n => false
  //}

  //def isDenseFringe(n:GlobalContainer) = n match {
    //case n:FringeDenseLoad => true
    //case n:FringeDenseStore => true
    //case n => false
  //}

  //def isSparseFringe(n:GlobalContainer) = n match {
    //case n:FringeSparseLoad => true
    //case n:FringeSparseStore => true
    //case n => false
  //}
//}
