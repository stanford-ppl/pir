package pir.node

trait GlobalContainer extends Container { self => }

case class CUContainer(contains:PIRNode*)(implicit design:PIRDesign) extends GlobalContainer
