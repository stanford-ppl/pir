package pir
package node

trait IO extends prism.node.Edge[PIRNode]() {
  type A = Primitive
}

class Input(implicit val src:Primitive, design:Design) extends prism.node.Input[PIRNode] with IO {
  val id = design.nextId
  def from = connected.head
  override def connect(p:prism.node.Edge[PIRNode]):this.type = {
    if (this.isInstanceOf[Input] && this.isConnected && !this.isConnectedTo(p))
      err(s"$this is already connected to ${connected}, reconnecting to $p")
    super.connect(p)
  }
}
class Output(implicit val src:Primitive, design:Design) extends prism.node.Output[PIRNode] with IO  {
  val id = design.nextId
  def to = connected
}
