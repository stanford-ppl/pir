package pir
package node

abstract class DRAMCommand(implicit env:Env) extends PIRNode {
  def dram:DRAM
  val respondValid = new OutputField[List[BufferRead]]("respondValid")
}

case class FringeDenseLoad(dram:DRAM)(implicit env:Env) extends DRAMCommand {
  val offset = new InputField[PIRNode]("offset")
  val size = new InputField[PIRNode]("size")
  val data = new OutputField[PIRNode]("data")
}

case class FringeSparseLoad(dram:DRAM)(implicit env:Env) extends DRAMCommand {
  val addr = new InputField[PIRNode]("addr")
  val data = new OutputField[PIRNode]("data")
}

case class FringeDenseStore(dram:DRAM)(implicit env:Env) extends DRAMCommand {
  val offset = new InputField[PIRNode]("offset")
  val size = new InputField[PIRNode]("size")
  val data = new InputField[PIRNode]("data")
  val valid = new InputField[PIRNode]("valid")
  val ack = new OutputField[PIRNode]("ack")
}

case class FringeSparseStore(dram:DRAM)(implicit env:Env) extends DRAMCommand {
  val addr = new InputField[PIRNode]("addr")
  val data = new InputField[PIRNode]("data")
  val ack = new OutputField[PIRNode]("ack")
}

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
