package pir.node

import pir._

import prism._
import prism.node._

abstract class PIRNode(implicit design:Design) extends ProductNode[PIRNode] with IR { self =>
  type N = PIRNode
  type P = Container
  type A = Primitive

  override def ins:List[Input]
  override def outs:List[Output]
  override def ios:List[IO] = ins ++ outs

  this match {
    case self:Top =>
    case self if !design.staging =>
    case self => setParent(design.top)
  }

  override def setParent(p:P):this.type = {
    this.parent match {
      case Some(top:Top) if p != top => unsetParent
      case p =>
    }
    super.setParent(p)
  }
}
