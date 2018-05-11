package pir
package node

trait Container extends PIRNode with prism.node.ProductSubGraph[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]
}

trait PIRContainer {
  def innerCtrlOf(container:Container) = {
    implicit val design = container.design.asInstanceOf[PIRDesign]
    import design.pirmeta._
    ctrlsOf(container).maxBy { _.ancestors.size }
  }
}
