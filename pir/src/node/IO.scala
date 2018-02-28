package pir.node

import pir._

import prism._
import prism.util._

trait IO extends prism.node.Edge[PIRNode]() {
  type A = Primitive
}

class Input(implicit val src:Primitive, design:Design) extends prism.node.Input[PIRNode] with IO {
  def from = connected.head
  override def connect(p:prism.node.Edge[PIRNode]):this.type = {
    err(this.isInstanceOf[Input] && this.isConnected && !this.isConnectedTo(p), s"$this is already connected to ${connected}, reconnecting to $p")
    super.connect(p)
  }
}
class Output(implicit val src:Primitive, design:Design) extends prism.node.Output[PIRNode] with IO  {
  def to = connected
}
