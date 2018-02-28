package pir.node

import pir._

import prism._
import prism.util._
import prism.enums._

abstract class Controller(implicit design:Design) extends prism.node.SubGraph[Controller] with IR {
  type P = Controller
  val style:ControlStyle
  val level:ControlLevel
  def isInnerControl = level==InnerControl 
  def isOuterControl = level==OuterControl
  val id = design.nextId
}
case class LoopController(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:Design) extends Controller {
  override def className = s"$style"
}
case class UnitController(style:ControlStyle, level:ControlLevel)(implicit design:Design) extends Controller
case class TopController()(implicit design:Design) extends Controller {
  val style = SeqPipe
  val level = OuterControl 
}
case class ArgInController()(implicit design:Design) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
