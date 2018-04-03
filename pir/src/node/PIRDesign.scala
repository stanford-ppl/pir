package pir.node

case class PIRDesign() extends prism.node.Design { 
  val pirmeta = new PIRMetadata

  val top = Top()
}
