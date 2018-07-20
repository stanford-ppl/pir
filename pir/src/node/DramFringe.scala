package pir
package node

abstract class DramFringe(implicit design:PIRDesign) extends GlobalContainer {
  val dram:List[DRAM]
}

case class FringeDenseLoad(
  dram:List[DRAM], 
  cmdStream:List[StreamOut], 
  dataStream:List[StreamIn]
)(implicit design:PIRDesign) extends DramFringe

case class FringeSparseLoad(
  dram:List[DRAM], 
  addrStream:List[StreamOut], 
  dataStream:List[StreamIn]
)(implicit design:PIRDesign) extends DramFringe

case class FringeDenseStore(
  dram:List[DRAM], 
  cmdStream:List[StreamOut], 
  dataStream:List[StreamOut], 
  ackStream:List[StreamIn]
)(implicit design:PIRDesign) extends DramFringe

case class FringeSparseStore(
  dram:List[DRAM], 
  cmdStream:List[StreamOut], 
  ackStream:List[StreamIn]
)(implicit design:PIRDesign) extends DramFringe

case class CountAck(ack:Def)(implicit design:PIRDesign) extends ControlNode

trait ProcessDramCommand extends Def
case class ProcessDramDenseLoad(dram:DRAM, offset:LocalLoad, size:LocalLoad)(implicit design:PIRDesign) extends ProcessDramCommand
case class ProcessDramDenseStore(dram:DRAM, offset:LocalLoad, size:LocalLoad, data:LocalLoad)(implicit design:PIRDesign) extends ProcessDramCommand
case class ProcessDramSparseLoad(dram:DRAM, addr:LocalLoad)(implicit design:PIRDesign) extends ProcessDramCommand
case class ProcessDramSparseStore(dram:DRAM, addr:LocalLoad, data:LocalLoad)(implicit design:PIRDesign) extends ProcessDramCommand

// HACK
case class DRAM(dims:List[PNode])(implicit design:PIRDesign) extends IR {
  val id = design.nextId
}

case class DramControllerDone(en:ControlNode)(implicit design:PIRDesign) extends ControlNode

trait DramFringeUtil {
  def isLoadFringe(n:GlobalContainer) = n match {
    case n:FringeDenseLoad => true
    case n:FringeSparseLoad => true
    case n => false
  }

  def isStoreFringe(n:GlobalContainer) = n match {
    case n:FringeDenseStore => true
    case n:FringeSparseStore => true
    case n => false
  }

  def isDenseFringe(n:GlobalContainer) = n match {
    case n:FringeDenseLoad => true
    case n:FringeDenseStore => true
    case n => false
  }

  def isSparseFringe(n:GlobalContainer) = n match {
    case n:FringeSparseLoad => true
    case n:FringeSparseStore => true
    case n => false
  }
}
