package pir
package node

abstract class Primitive(implicit design: PIRDesign) extends PIRNode with prism.node.ProductAtom[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  override def newIn = new Input
  override def newOut = new Output

}

