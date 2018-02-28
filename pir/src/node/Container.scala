package pir.node

import pir._

import prism._
import prism.util._
import prism.node._

trait Container extends ProductNode[PIRNode] with ProductSubGraph[PIRNode] with PIRNode { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]
}
