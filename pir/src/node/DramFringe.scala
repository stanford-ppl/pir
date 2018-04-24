package pir.node

trait DramFringe extends GlobalContainer {
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
