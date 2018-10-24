package pir
package node

abstract class DRAMFringe(implicit env:Env) extends PIRNode {
  def dram:DRAM
}

case class FringeDenseLoad(dram:DRAM)(implicit env:Env) extends DRAMFringe {
  val offset = new InputField[Access]
  val size = new InputField[Access]
  val data = new OutputField[Access]
}

case class FringeSparseLoad(dram:DRAM)(implicit env:Env) extends DRAMFringe {
  val addr = new InputField[Access]
  val data = new OutputField[Access]
}

case class FringeDenseStore(dram:DRAM)(implicit env:Env) extends DRAMFringe {
  val offset = new InputField[Access]
  val size = new InputField[Access]
  val data = new InputField[Access]
  val ack = new OutputField[Access]
}

case class FringeSparseStore(dram:DRAM)(implicit env:Env) extends DRAMFringe {
  val addr = new InputField[Access]
  val data = new InputField[Access]
  val ack = new OutputField[Access]
}

case class DRAM() extends prism.graph.IR

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
