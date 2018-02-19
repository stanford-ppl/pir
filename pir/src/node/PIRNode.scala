package pir.node

import pir._

import pirc._
import pirc.enums._

abstract class PIRNode(implicit design:PIR) extends prism.node.Node[PIRNode] with IR { self =>
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

sealed trait IOType extends Enum
case object Vector extends IOType
case object Scalar extends IOType
case object Control extends IOType

sealed trait ControlStyle extends Enum
case object InnerPipe extends ControlStyle
case object SeqPipe extends ControlStyle
case object MetaPipe extends ControlStyle
case object StreamPipe extends ControlStyle
case object ForkSwitch extends ControlStyle

sealed trait ControlLevel extends Enum
case object InnerControl extends ControlLevel
case object OuterControl extends ControlLevel

