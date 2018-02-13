package pir.node

import pir._

import pirc._
import pirc.util._

abstract class IO(override val src:Primitive)(implicit design:PIR) extends prism.node.Edge[PIRNode]() {
  override type A = Primitive
  override def connect(p:prism.node.Edge[PIRNode]):this.type = {
    err(this.isInstanceOf[Input] && this.isConnected && !this.isConnectedTo(p), s"$this is already connected to ${connected}, reconnecting to $p")
    super.connect(p)
  }
}
class Input(implicit src:Primitive, design:PIR) extends IO(src) with prism.node.Input[PIRNode] {
  type E = Output
  def from = connected.head
  override def connect(e:prism.node.Edge[PIRNode]):this.type = {
    val p = e.asInstanceOf[E]
    super.connect(p)
  }
}
class Output(implicit src:Primitive, design:PIR) extends IO(src) with prism.node.Output[PIRNode] {
  type E = Input
  def to = connected
  override def connect(e:prism.node.Edge[PIRNode]):this.type = {
    val p = e.asInstanceOf[E]
    super.connect(p)
  }
}

