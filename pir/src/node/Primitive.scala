package pir.node

import pir._

import prism._
import prism.util._
import prism.node._

abstract class Primitive(implicit design: Design) extends PIRNode with ProductAtom[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  override def newIn = new Input
  override def newOut = new Output

}

