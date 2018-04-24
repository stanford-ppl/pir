package pir.node
import prism.enums._

abstract class Controller(implicit design:PIRDesign) extends prism.node.SubGraph[Controller] with IR {
  type P = Controller
  val style:ControlStyle
  val level:ControlLevel
  def isInnerControl = level==InnerControl 
  def isOuterControl = level==OuterControl
  val id = design.nextId
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
case class DramController(par:Int)(implicit design:PIRDesign) extends Controller {
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

