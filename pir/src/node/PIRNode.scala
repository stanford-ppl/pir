package pir.node

import pir._

import pirc._
import prism.node._

trait PIRNode extends ProductNode[PIRNode] with IR { self =>
  type N = PIRNode
  type P = Container
  type A = Primitive

  override def ins:List[Input]
  override def outs:List[Output]
  override def ios:List[IO] = ins ++ outs

  this match {
    case self:Design =>
    case self if !design.staging =>
    case self => setParent(design.asInstanceOf[PIRDesign])
  }

  override def setParent(p:P):this.type = {
    this.parent match {
      case Some(top:Design) if p != top => unsetParent
      case p =>
    }
    super.setParent(p)
  }
}

