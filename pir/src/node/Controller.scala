package pir
package node

import prism.enums._

abstract class Controller(implicit design:PIRDesign) extends prism.node.SubGraph[Controller] with IR {
  type P = Controller
  val style:ControlStyle
  val level:ControlLevel
  def isInnerControl = children.isEmpty
  def isOuterControl = !isInnerControl
  def isStream = style==StreamPipe
  val id = design.nextId
}
case class ForeverController()(implicit design:PIRDesign) extends Controller {
  val style = StreamPipe
  val level = OuterControl
}
case class LoopController(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIRDesign) extends Controller {
  override def className = s"$style"
}
case class UnitController(style:ControlStyle, level:ControlLevel)(implicit design:PIRDesign) extends Controller
case class TopController()(implicit design:PIRDesign) extends Controller {
  val style = SeqPipe
  val level = OuterControl 
}
case class ArgInController()(implicit design:PIRDesign) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
case class ArgOutController()(implicit design:PIRDesign) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
/*
 * @size constant propogated size in bytes
 * */
case class DramController(size:Option[Int], par:Int)(implicit design:PIRDesign) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}

sealed trait ControlStyle extends Enum
case object InnerPipe extends ControlStyle
case object SeqPipe extends ControlStyle
case object MetaPipe extends ControlStyle
case object StreamPipe extends ControlStyle
case object ForkSwitch extends ControlStyle

sealed trait ControlLevel extends Enum
case object InnerControl extends ControlLevel
case object OuterControl extends ControlLevel

trait ControllerUtil {

  def isForever(n:Controller) = n match {
    case n:ForeverController => true
    case n => false
  }

}
