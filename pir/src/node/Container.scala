package pir.node

import pir._

import pirc._
import pirc.util._

abstract class Container(implicit design:PIR) extends PIRNode with prism.node.SubGraph[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    implicit val ev = nct
    x match {
      case x:N => this.addChild(x); x
      case x => super.connectFields(x, i)
    }
  }
}
