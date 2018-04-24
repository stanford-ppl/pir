package pir.node

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
  actStream:List[StreamIn]
)(implicit design:PIRDesign) extends DramFringe

case class FringeSparseStore(
  dram:List[DRAM], 
  cmdStream:List[StreamOut], 
  actStream:List[StreamIn]
)(implicit design:PIRDesign) extends DramFringe

case class DRAM()(implicit design:PIRDesign) extends IR {
  val id = design.nextId
}

case class DramControllerDone(en:ControlNode)(implicit design:PIRDesign) extends ControlNode

trait PIRDramFringe {
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
}
